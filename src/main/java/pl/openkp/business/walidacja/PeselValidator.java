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
public class PeselValidator implements ConstraintValidator<Pesel, String> {

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext context) {
        if (pesel == null) {
            return true;
        }
        pesel = pesel.replaceAll("\\D*", "");
        int psize = pesel.length();
        if (psize != 11) {
            return false;
        }
        int[] weights = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };
        int j = 0, sum = 0, control = 0;
        int csum = new Integer(pesel.substring(psize - 1)).intValue();
        for (int i = 0; i < psize - 1; i++) {
            char c = pesel.charAt(i);
            j = new Integer(String.valueOf(c)).intValue();
            sum += j * weights[i];
        }
        control = 10 - (sum % 10);
        if (control == 10) {
            control = 0;
        }
        return (control == csum);
    }

    @Override
    public void initialize(Pesel constraintAnnotation) {

    }
}
