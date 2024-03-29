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
package org.doodle.pipeline.autoconfigure.client;

import org.doodle.broker.autoconfigure.client.BrokerClientAutoConfiguration;
import org.doodle.broker.client.BrokerClientRSocketRequester;
import org.doodle.pipeline.client.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration(after = BrokerClientAutoConfiguration.class)
@ConditionalOnClass(PipelineClientProperties.class)
@EnableConfigurationProperties(PipelineClientProperties.class)
public class PipelineClientAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  public PipelineClientMapper pipelineClientMapper() {
    return new PipelineClientMapper();
  }

  @AutoConfiguration
  @ConditionalOnClass(RestTemplate.class)
  @ConditionalOnBean(RestTemplate.class)
  public static class ServletConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PipelineClientServlet pipelineClientServlet(RestTemplate template) {
      return new PipelineClientServletImpl(template);
    }
  }

  @AutoConfiguration
  @ConditionalOnClass(BrokerClientRSocketRequester.class)
  @ConditionalOnBean(BrokerClientRSocketRequester.class)
  public static class RSocketConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PipelineClientRSocket pipelineClientRSocket(
        BrokerClientRSocketRequester requester, PipelineClientProperties properties) {
      return new BrokerPipelineRSocket(requester, properties);
    }
  }
}
