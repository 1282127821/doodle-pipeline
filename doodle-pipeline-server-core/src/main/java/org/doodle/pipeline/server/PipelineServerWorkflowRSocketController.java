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
public class PipelineServerWorkflowRSocketController
    implements PipelineWorkflowQueryOps.RSocket, PipelineWorkflowPageOps.RSocket {
  PipelineServerWorkflowService workflowService;

  @MessageMapping(PipelineWorkflowPageOps.RSocket.PAGE_MAPPING)
  @Override
  public Mono<PipelineWorkflowQueryReply> query(PipelineWorkflowQueryRequest request) {
    return Mono.empty();
  }

  @MessageExceptionHandler(PipelineServerExceptions.Query.class)
  Mono<PipelineWorkflowQueryReply> onQueryException(PipelineServerExceptions.Query ignored) {
    return Mono.just(
        PipelineWorkflowQueryReply.newBuilder().setError(PipelineErrorCode.FAILURE).build());
  }

  @MessageMapping(PipelineWorkflowQueryOps.RSocket.QUERY_MAPPING)
  @Override
  public Mono<PipelineWorkflowPageReply> page(PipelineWorkflowPageRequest request) {
    return Mono.empty();
  }

  @MessageExceptionHandler(PipelineServerExceptions.Page.class)
  Mono<PipelineWorkflowPageReply> onPageException(PipelineServerExceptions.Page ignored) {
    return Mono.just(
        PipelineWorkflowPageReply.newBuilder().setError(PipelineErrorCode.FAILURE).build());
  }
}
