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
package org.doodle.pipeline.server;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.doodle.design.pipeline.*;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PipelineServerAgentRSocketController
    implements PipelineAgentCreateOps.RSocket, PipelineAgentPageOps.RSocket {
  PipelineServerAgentService agentService;

  @MessageMapping(PipelineAgentCreateOps.RSocket.CREATE_MAPPING)
  @Override
  public Mono<PipelineAgentCreateReply> create(PipelineAgentCreateRequest request) {
    return Mono.empty();
  }

  @MessageExceptionHandler(PipelineServerExceptions.Create.class)
  Mono<PipelineAgentCreateReply> onCreateException(PipelineServerExceptions.Create ignored) {
    return Mono.just(
        PipelineAgentCreateReply.newBuilder().setError(PipelineErrorCode.FAILURE).build());
  }

  @MessageMapping(PipelineAgentPageOps.RSocket.PAGE_MAPPING)
  @Override
  public Mono<PipelineAgentPageReply> page(PipelineAgentPageRequest request) {
    return Mono.empty();
  }

  @MessageExceptionHandler(PipelineServerExceptions.Page.class)
  Mono<PipelineAgentPageReply> onPageException(PipelineServerExceptions.Page ignored) {
    return Mono.just(
        PipelineAgentPageReply.newBuilder().setError(PipelineErrorCode.FAILURE).build());
  }
}
