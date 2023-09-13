/*
 * Copyright (c) 2022-present Doodle. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.doodle.pipeline.autoconfigure.server;

import org.doodle.design.broker.rsocket.BrokerRSocketRequester;
import org.doodle.pipeline.server.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@AutoConfiguration
@ConditionalOnClass(PipelineServerProperties.class)
@EnableConfigurationProperties(PipelineServerProperties.class)
@EnableMongoAuditing
@EnableMongoRepositories(
    basePackageClasses = {PipelineServerAgentRepo.class, PipelineServerWorkflowRepo.class})
public class PipelineServerAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  public PipelineServerMapper pipelineServerMapper() {
    return new PipelineServerMapper();
  }

  @Bean
  @ConditionalOnMissingBean
  public PipelineServerAgentService pipelineServerAgentService(PipelineServerAgentRepo agentRepo) {
    return new PipelineServerAgentService(agentRepo);
  }

  @Bean
  @ConditionalOnMissingBean
  public PipelineServerWorkflowService pipelineServerWorkflowService(
      PipelineServerWorkflowRepo workflowRepo) {
    return new PipelineServerWorkflowService(workflowRepo);
  }

  @AutoConfiguration
  @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
  public static class ServletConfinguration {
    @Bean
    @ConditionalOnMissingBean
    public PipelineServerAgentServletController pipelineServerAgentServletController(
        PipelineServerAgentService agentService) {
      return new PipelineServerAgentServletController(agentService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PipelineServerWorkflowServletController pipelineServerWorkflowServletController(
        PipelineServerWorkflowService workflowService) {
      return new PipelineServerWorkflowServletController(workflowService);
    }
  }

  @AutoConfiguration
  @ConditionalOnClass(BrokerRSocketRequester.class)
  @ConditionalOnBean(BrokerRSocketRequester.class)
  public static class RSocketConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PipelineServerAgentRSocketController pipelineServerAgentRSocketController(
        PipelineServerAgentService agentService) {
      return new PipelineServerAgentRSocketController(agentService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PipelineServerWorkflowRSocketController pipelineServerWorkflowRSocketController(
        PipelineServerWorkflowService workflowService) {
      return new PipelineServerWorkflowRSocketController(workflowService);
    }
  }
}
