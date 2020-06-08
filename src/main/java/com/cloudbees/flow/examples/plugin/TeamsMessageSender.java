package com.cloudbees.flow.examples.plugin;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 * Class used to send messages to a Teams channel using incoming Webhooks.
 * Microsoft Teams defines 1 WebHook URL per channel.
 */
public class TeamsMessageSender {

  private String urlHook;

  public TeamsMessageSender(String urlHook) {
    this.urlHook = urlHook;
  }

  public String sendMessage(String message) {

    return Unirest.post(urlHook)
            .header("Content-Type", "application/json")
            .body(message)
            .asString()
            .getBody();
  }
}
