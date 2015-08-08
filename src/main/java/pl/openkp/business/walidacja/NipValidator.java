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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Walidator dla numeru pesel
 * 
 * @author Andrzej Szywała
 */
public class NipValidator implements ConstraintValidator<Nip, String> {

    @Override
    public boolean isValid(String nip, ConstraintValidatorContext context) {
        if (nip == null) {
            return true;
        }
        nip = nip.replaceAll("\\D*", "");
        int nsize = nip.length();
        if (nsize != 10) {
            return false;
        }
        int[] weights = { 6, 5, 7, 2, 3, 4, 5, 6, 7 };
        int j = 0, sum = 0, control = 0;
        int csum = new Integer(nip.substring(nsize - 1)).intValue();
        if (csum == 0) {
            csum = 10;
        }
        for (int i = 0; i < nsize - 1; i++) {
            char c = nip.charAt(i);
            j = new Integer(String.valueOf(c)).intValue();
            sum += j * weights[i];
        }
        control = sum % 11;
        return (control == csum);
    }

    @Override
    public void initialize(Nip constraintAnnotation) {

    }
}
