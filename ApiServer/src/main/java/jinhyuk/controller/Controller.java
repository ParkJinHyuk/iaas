package jinhyuk.controller;

import jinhyuk.dto.request.CreateClusterRequestDto;
import jinhyuk.dto.request.CreateNodeRequestDto;
import jinhyuk.dto.request.UpdateClusterRequestDto;
import jinhyuk.dto.response.ClusterResponseDto;
import jinhyuk.service.node.NodesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jinhyuk.service.ClustersService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@RestController
public class Controller {

    private final NodesService nodesService;
    private final ClustersService clustersService;

    // 클러스터 정보 조회
    @GetMapping("/clusters")
    public ResponseEntity<List<ClusterResponseDto>> getCluster() {
        return ResponseEntity.ok(clustersService.getCluster());
    }


    // 클러스터 생성
    @PostMapping("/clusters")
    public ResponseEntity<String> createCluster(@Valid @RequestBody CreateClusterRequestDto requestDto) {
        clustersService.createCluster(requestDto);
        return ResponseEntity.ok("Success Create Cluster");
    }


    // 클러스터 정책 변경
    @PutMapping("/clusters/{id}")
    public ResponseEntity<String> updateCluster(@PathVariable Long id, @Valid @RequestBody UpdateClusterRequestDto requestDto) throws IllegalAccessException {
        clustersService.updateCluster(id, requestDto);
        return ResponseEntity.ok("Success Update Cluster");
    }


    // 클러스터에 노드 추가(스케일아웃)
    @PostMapping("/clusters/{id}")
    public ResponseEntity<String> createNode(@PathVariable Long id, @Valid @RequestBody CreateNodeRequestDto requestDto) throws IllegalAccessException {
        clustersService.createNode(id, requestDto);
        return ResponseEntity.ok("Success Create Node");
    }


    // 클러스터 삭제(스케일인)
    @DeleteMapping("/clusters/{id}")
    public ResponseEntity<String> deleteCluster(@PathVariable Long id) throws IllegalAccessException {
        clustersService.deleteCluster(id);
        return ResponseEntity.ok("Success Delete Cluster");
    }


    // 클러스터에 노드 삭제(스케일인)
    @DeleteMapping("/nodes/{id}")
    public ResponseEntity<String> deleteNode(@PathVariable Long id) throws IllegalAccessException {
        nodesService.deleteNode(id);
        return ResponseEntity.ok("Success Delete Node");
    }


}
