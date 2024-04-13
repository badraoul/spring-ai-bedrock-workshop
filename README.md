# Spring AI Workshop for AWS Bedrock Cohere

## Introduction
This is a copy of the excellent [spring-a--azure-workshop](https://github.com/Azure-Samples/spring-ai-azure-workshop/tree/main) that has been ported to work with AWS Bedrock.  Unlike vendors like Azure and Google, AWS has chosen to host multiple models rather than throw in entirely on their own model (Titan).  The model I selected for this workshop is 'Cohere' becuase it has SpringAI support for chat and embeddings.

**NOTE:** I have everything in the library working except RAG.  That is a work in progress and this repo will be updated when that part is sorted out.

## Prerequisites

Before you begin make sure to set the following environment variables.

```shell
export AWS_ACCESS_KEY_ID=<INSERT ID HERE>
export AWS_SECRET_ACCESS_KEY=<INSERT KEY HERE>
export AWS_SESSION_TOKEN=<INSERT TOKEN HERE>
```
NOTE: the docs will tell you tp enter these values in th application.properties file.  
I found I had issues with the utility used in house to access AWS, so I leave them out of application.properties.   However, you must set the region in the application.properties file.

### Create Bedrock AI Deployments

The configuration assumes you have already enabled Cohere inside your AWS Bedrock account.  You can enable model access at the model access tab on the bottom-left of teh main page.  e.g. [here](https://us-east-1.console.aws.amazon.com/bedrock/home?region=us-east-1#/modelaccess)


NOTE: Spring configuration properties currently use the property-name `model` instead of `deployment-name` so don't get confused.  Spring AI will be renaming the property in the future to avoid confusion.  See [here](https://docs.spring.io/spring-ai/reference/api/clients/azure-openai-chat.html#_deployment_name) for more information.

For the chat, the configuration in `application.properties` should contain the following

```shell
spring.ai.bedrock.aws.region=us-east-1

spring.ai.bedrock.cohere.embedding.enabled=true
spring.ai.bedrock.cohere.chat.enabled=true
spring.ai.bedrock.cohere.chat.options.temperature=0.8
```

## Workshop Overview

The workshop consists of six examples, each with a dedicated `README` file.

All six workshop examples are organized into individual Java packages within this project. In each package, you'll find a Spring @RestController class that serves as the entry point for showcasing the discussed functionality.

To interact with the @RestController, you will be using the `http` utility as a user-friendly alternative to `curl`.

Detailed instructions and exercises for each example can be found in their respective README files:

* 1-README-tell-me-a-joke.md 
* 2-README-prompt-templating.md 
* 3-README-prompt-roles.md 
* 4-README-output-parser.md 
* 5-README-stuff-prompt.md 
* 6-README-retrieval-augmented-generation.md

These guides will walk you through the workshop exercises.