package com.xkcd.ai.roles;

import org.springframework.ai.bedrock.cohere.BedrockCohereChatClient;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RoleController {

	// private final ChatClient chatClient;
	private final BedrockCohereChatClient chatClient;

	@Value("classpath:/prompts/system-message.st")
	private Resource systemResource;

	@Autowired
	// public RoleController(ChatClient chatClient) {
	public RoleController(BedrockCohereChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@GetMapping("/ai/roles")
	public AssistantMessage generate(@RequestParam(value = "message",
			defaultValue = "Tell me about three famous pirates from the Golden Age of Piracy and why they did.  Write at least a sentence for each pirate.") String message,
			@RequestParam(value = "name", defaultValue = "Bob") String name,
			@RequestParam(value = "voice", defaultValue = "pirate") String voice) {
		UserMessage userMessage = new UserMessage(message);
		SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemResource);
		Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", name, "voice", voice));
		Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
		return chatClient.call(prompt).getResult().getOutput();
	}

}
