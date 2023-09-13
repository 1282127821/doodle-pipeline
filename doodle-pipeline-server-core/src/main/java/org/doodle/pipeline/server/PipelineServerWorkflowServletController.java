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
import org.doodle.design.pipeline.PipelineWorkflowPageOps;
import org.doodle.design.pipeline.PipelineWorkflowQueryOps;
import org.doodle.design.pipeline.model.payload.reply.PipelineWorkflowPageReply;
import org.doodle.design.pipeline.model.payload.reply.PipelineWorkflowQueryReply;
import org.doodle.design.pipeline.model.payload.request.PipelineWorkflowPageRequest;
import org.doodle.design.pipeline.model.payload.request.PipelineWorkflowQueryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PipelineServerWorkflowServletController
    implements PipelineWorkflowQueryOps.Servlet, PipelineWorkflowPageOps.Servlet {
  PipelineServerWorkflowService workflowService;

  @PostMapping(PipelineWorkflowQueryOps.Servlet.QUERY_MAPPING)
  @Override
  public Result<PipelineWorkflowPageReply> page(PipelineWorkflowPageRequest request) {
    return Result.bad();
  }

  @PostMapping(PipelineWorkflowPageOps.Servlet.PAGE_MAPPING)
  @Override
  public Result<PipelineWorkflowQueryReply> query(PipelineWorkflowQueryRequest request) {
    return Result.bad();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Result<Void>> onException(Exception ignored) {
    return ResponseEntity.badRequest().body(Result.bad());
  }
}
