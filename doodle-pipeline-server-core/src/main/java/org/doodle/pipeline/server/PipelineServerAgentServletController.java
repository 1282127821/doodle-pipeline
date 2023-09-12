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
import org.doodle.design.common.Result;
import org.doodle.design.pipeline.PipelineAgentCreateOps;
import org.doodle.design.pipeline.PipelineAgentPageOps;
import org.doodle.design.pipeline.model.payload.reply.PipelineAgentCreateReply;
import org.doodle.design.pipeline.model.payload.reply.PipelineAgentPageReply;
import org.doodle.design.pipeline.model.payload.request.PipelineAgentCreateRequest;
import org.doodle.design.pipeline.model.payload.request.PipelineAgentPageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PipelineServerAgentServletController
    implements PipelineAgentCreateOps.Servlet, PipelineAgentPageOps.Servlet {
  PipelineServerAgentService agentService;

  @PostMapping(PipelineAgentCreateOps.Servlet.CREATE_MAPPING)
  @Override
  public Result<PipelineAgentCreateReply> create(PipelineAgentCreateRequest request) {
    return Result.bad();
  }

  @PostMapping(PipelineAgentPageOps.Servlet.PAGE_MAPPING)
  @Override
  public Result<PipelineAgentPageReply> page(PipelineAgentPageRequest request) {
    return Result.bad();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Result<Void>> onException(Exception ignored) {
    return ResponseEntity.badRequest().body(Result.bad());
  }
}
