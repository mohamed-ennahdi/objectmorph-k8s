package com.github.mohamedennahdi.objectmorph.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SourceCodeDto(

		@Schema(example= "Test.java", description = "File name of the source code")
		String filename,

		@Schema(example= "class SourceCode { int attribute1; int attribute2; SourceCode(){} public int getAttribute1() { return attribute1;}}", description = "Source code")
		String sourceCode
		) {}
