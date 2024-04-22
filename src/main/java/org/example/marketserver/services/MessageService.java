package org.example.marketserver.services;

import org.example.marketserver.dtos.MessageDTO;
import org.example.marketserver.models.Message;
import org.example.marketserver.repositories.MessageRepository;
import org.example.marketserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setSender(userRepository.getById(messageDTO.getSenderId()));
        message.setReceiver(userRepository.getById(messageDTO.getReceiverId()));
        message.setContent(messageDTO.getContent());
        message.setTimestamp(messageDTO.getTimestamp());
        Message savedMessage = messageRepository.save(message);
        return mapToDTO(savedMessage);
    }

    public List<MessageDTO> getMessagesBetweenUsers(Long senderId, Long receiverId) {
        List<Message> messages = messageRepository.findBySenderIdAndReceiverIdOrderByTimestampAsc(senderId, receiverId);
        return messages.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public Long getMessageByRecip(Long recipientId) {
        Message message = messageRepository.findByRecipientId(recipientId)
                .orElseThrow(() -> new RuntimeException("Message not found for recipient ID " + recipientId));
        return message.getReceiver().getId();
    }

    private MessageDTO mapToDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setId(message.getId());
        dto.setSenderId(message.getSender().getId());
        dto.setReceiverId(message.getReceiver().getId());
        dto.setContent(message.getContent());
        dto.setTimestamp(message.getTimestamp());
        return dto;
    }
}
