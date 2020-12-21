package jinhyuk.service.remote;

import com.jcraft.jsch.*;
import jinhyuk.dto.data.ServerInstance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class RemoterTasker {

    private final JSch jSch;

    // 세션 생성
    public Session openSession(ServerInstance serverInstance, String password) throws JSchException {
        int port = 22;
        String user = "root";
        String host = serverInstance.getPrivateIp();
        Session session = jSch.getSession(user, host, port);
        session.setPassword(password);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setTimeout(15000);
        session.connect();
        return session;
    }


    // host 이름 등록
    public void writeHosts(List<ServerInstance> serverInstanceList, Session session) throws JSchException, SftpException, IOException {
        Channel channel = session.openChannel("sftp");
        ChannelSftp channelSftp = (ChannelSftp) channel;
        channelSftp.connect();
        InputStream inputStream = channelSftp.get("/etc/hosts");
        File file = new File("hosts");
        FileUtils.copyInputStreamToFile(inputStream, file);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        for(ServerInstance serverInstance : serverInstanceList) {
            bw.write("\n" + serverInstance.getPrivateIp() + "\t" + serverInstance.getServerName());
        }
        bw.close();
        inputStream = new FileInputStream(file);
        channelSftp.put(inputStream, "/etc/hosts");
        inputStream.close();
        channelSftp.disconnect();
        channel.disconnect();
    }


    // 클러스터링 명령어 실행
    public void joinCluster(String serverName, Session session) throws JSchException {
        log.info("join 명령어를 실행합니다.");
        String command = "rabbitmqctl stop_app" + "&&" +
                         "rabbitmqctl join_cluster rabbit@" + serverName  + "&&" +
                         "rabbitmqctl start_app";

        Channel channel = session.openChannel("exec");
        ChannelExec channelExec = (ChannelExec) channel;
        channelExec.setCommand(command);
        channelExec.connect();

        channelExec.disconnect();
        channel.disconnect();
    }


    // 클러스터링 해제 명령어 실행
    public void leaveCluster(Session session) throws JSchException {
        log.info("reset 명령어를 실행합니다.");
        String command = "rabbitmqctl stop_app &&" +
                         "rabbitmqctl reset";

        Channel channel = session.openChannel("exec");
        ChannelExec channelExec = (ChannelExec) channel;
        channelExec.setCommand(command);
        channelExec.connect();

        channelExec.disconnect();
        channel.disconnect();
    }


    // 클러스터링 원격 해제 명령어 실행
    public void forgetCluster(String serverName, Session session) throws JSchException {
        log.info("forget 명령어를 실행합니다.");
        String command = "rabbitmqctl forget_cluster_node rabbit@" + serverName;

        Channel channel = session.openChannel("exec");
        ChannelExec channelExec = (ChannelExec) channel;
        channelExec.setCommand(command);
        channelExec.connect();

        channelExec.disconnect();
        channel.disconnect();
    }


    // 정책 설정
    public void setPolicy(String policy, int nodeCnt, Session session) throws JSchException {
        log.info(policy + " policy 명령어를 실행합니다.");
        String command = null;
        if(policy.equals("all")) {
            command = "rabbitmqctl set_policy ha-all \"^ha\\.\" '{\"ha-mode\":\"" + policy + "\"}'";
        } else if(policy.equals("exactly")) {
            command = "rabbitmqctl set_policy ha-all \"^ha\\.\" '{\"ha-mode\":\"" + policy + "\"," +
                    "\"ha-params\":" + ((nodeCnt / 2) + 1) + "}'";
        }

        Channel channel = session.openChannel("exec");
        ChannelExec channelExec = (ChannelExec) channel;
        channelExec.setCommand(command);
        channelExec.connect();

        channelExec.disconnect();
        channel.disconnect();
    }
}
