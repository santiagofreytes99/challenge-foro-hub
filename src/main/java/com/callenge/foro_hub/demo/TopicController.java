package com.callenge.foro_hub.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/api/topics")
    public class TopicController {

        @Autowired
        private TopicService topicService;

        @GetMapping
        public List<Topic> getAllTopics() {
            return topicService.getAllTopics();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
            Optional<Topic> topic = topicService.getTopicById(id);
            return topic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public Topic createTopic(@Valid @RequestBody Topic topic) {
            return topicService.createTopic(topic);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @Valid @RequestBody Topic topicDetails) {
            return ResponseEntity.ok(topicService.updateTopic(id, topicDetails));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
            topicService.deleteTopic(id);
            return ResponseEntity.noContent().build();
        }
    }