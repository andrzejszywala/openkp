/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.openkp.business.walidacja;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author Andrzej Szywała
 */
public class IbanValidatorTest {

    @Test
    public void ibanPoprawny() {
        String iban = "PL12 1240 4315 1111 0010 2942 2840";

        assertTrue(new IbanValidator().isValid(iban, null));
    }

    @Test
    public void ibanNiePoprawny() {
        String iban = "58425698524";

        assertFalse(new IbanValidator().isValid(iban, null));
    }
}
