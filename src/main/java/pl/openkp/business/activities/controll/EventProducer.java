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
package pl.openkp.business.activities.controll;

import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import pl.openkp.business.activities.entity.ActivityEvent;
import pl.openkp.business.activities.entity.WBActivityEvent;

@Stateless
public class EventProducer {

    @Inject
    private Logger logger;

    @Inject
    @WBActivityEvent
    Event<ActivityEvent> activityEvent;

    @Schedule(second = "*/40", minute = "*", hour = "*")
    public void produceActivityEvent(Timer t) {
        logger.info("Producing activity event");
        activityEvent.fire(new ActivityEvent());
    }
}
