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
package org.doodle.pipeline.client;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.doodle.broker.client.BrokerClientRSocketRequester;
import org.doodle.design.broker.frame.BrokerFrame;
import org.doodle.design.broker.frame.BrokerFrameMimeTypes;
import org.doodle.design.broker.frame.BrokerFrameUtils;
import org.doodle.design.pipeline.*;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrokerPipelineRSocket implements PipelineClientRSocket {
  BrokerClientRSocketRequester requester;
  BrokerFrame frame;

  public BrokerPipelineRSocket(
      BrokerClientRSocketRequester requester, PipelineClientProperties properties) {
    this.requester = requester;
    this.frame = BrokerFrameUtils.unicast(properties.getServer().getTags());
  }

  @Override
  public Mono<PipelineAgentCreateReply> create(PipelineAgentCreateRequest request) {
    return route(PipelineAgentCreateOps.RSocket.CREATE_MAPPING)
        .data(request)
        .retrieveMono(PipelineAgentCreateReply.class);
  }

  @Override
  public Mono<PipelineAgentPageReply> page(PipelineAgentPageRequest request) {
    return route(PipelineAgentPageOps.RSocket.PAGE_MAPPING)
        .data(request)
        .retrieveMono(PipelineAgentPageReply.class);
  }

  @Override
  public Mono<PipelineWorkflowQueryReply> query(PipelineWorkflowQueryRequest request) {
    return route(PipelineWorkflowQueryOps.RSocket.QUERY_MAPPING)
        .data(request)
        .retrieveMono(PipelineWorkflowQueryReply.class);
  }

  @Override
  public Mono<PipelineWorkflowPageReply> page(PipelineWorkflowPageRequest request) {
    return route(PipelineWorkflowPageOps.RSocket.PAGE_MAPPING)
        .data(request)
        .retrieveMono(PipelineWorkflowPageReply.class);
  }

  protected RSocketRequester.RequestSpec route(String route) {
    return requester.route(route).metadata(frame, BrokerFrameMimeTypes.BROKER_FRAME_MIME_TYPE);
  }
}
