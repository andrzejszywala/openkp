/**
 * Licensed under the Apache License, Version 2.0 (the \"License\");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an \"AS IS\" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.openkp.business.activities.boundary;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import pl.openkp.business.activities.entity.ActivityEvent;
import pl.openkp.business.activities.entity.WBActivityEvent;

@Singleton
@ServerEndpoint("/messages")
public class ActivityEndpoint {

    @Inject
    private Logger logger;

    private Set<Session> peers = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Opening session " + session.getId());
        peers.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        logger.info("Closing session " + session.getId());
        peers.remove(session);
    }

    @OnMessage
    private void onMessage(String message, Session session) {
        logger.info("message received " + message);
        try {

            session.getBasicRemote()
                    .sendText(
                            "{\"id\":7,\"image\":\"278159e2-6376-43e4-980a-50c4962b270c\",\"title\":\"2301ab36-74c5-4078-9440-2812270aa565\",\"description\":\"b38aead3-b7f2-42d9-9dbb-f4fdcf3a9f1f\",\"date\":1433605151996}");
        } catch (IOException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    public void onActivityEvent(@Observes @WBActivityEvent ActivityEvent event) {
        logger.info("Activity event observed " + event.getTimestamp());
        peers.forEach((peer) -> sendMessage(peer, event));
    }

    private void sendMessage(Session peer, ActivityEvent event) {
        try {
            logger.info("Sending message to " + peer.getId());
            peer.getBasicRemote()
                    .sendText(
                            "{\"id\":7,\"image\":\"278159e2-6376-43e4-980a-50c4962b270c\",\"title\":\"2301ab36-74c5-4078-9440-2812270aa565\",\"description\":\"b38aead3-b7f2-42d9-9dbb-f4fdcf3a9f1f\","
                                    + "\"date\": " + event.getTimestamp().getTime() + "}");
        } catch (ClosedChannelException cce) {
            logger.info("Session " + peer.getId() + " is closed.");
            peers.remove(peer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }
}
