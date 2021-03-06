/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.logback;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import org.springframework.boot.test.extension.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleLogbackApplicationTests {

	@RegisterExtension
	OutputCapture output = new OutputCapture();

	@Test
	public void testLoadedCustomLogbackConfig() throws Exception {
		SampleLogbackApplication.main(new String[0]);
		assertThat(this.output).contains("Sample Debug Message");
		assertThat(this.output).doesNotContain("Sample Trace Message");
	}

	@Test
	public void testProfile() throws Exception {
		SampleLogbackApplication
				.main(new String[] { "--spring.profiles.active=staging" });
		assertThat(this.output).contains("Sample Debug Message");
		assertThat(this.output).contains("Sample Trace Message");
	}

}
