package com.cloudbees.flow.examples.plugin;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 * Class used to send messages to a Teams channel using incoming Webhooks.
 * Microsoft Teams defines 1 WebHook URL per channel.
 */
public class TeamsMessageSender {

  private String urlHook;
  private String proxyHost;
  private String proxyPort;
  private String proxyLogin;
  private String proxyPassword;

  public TeamsMessageSender(String urlHook) {
    this.urlHook = urlHook;
  }

  public void addProxy(String proxyHost, String proxyPort, String proxyLogin, String proxyPassword) {
    if (proxyHost != null && !"".equals(proxyHost) && (proxyLogin == null || "".equals(proxyHost)) ) {
      Unirest.config()
              .proxy(proxyHost, Integer.parseInt(proxyPort));
    } else if (proxyHost != null && !"".equals(proxyHost) && proxyLogin != null && !"".equals(proxyHost) ) {
      Unirest.config()
              .proxy(proxyHost, Integer.parseInt(proxyPort), proxyLogin, proxyPassword);
    }
  }

  public String sendMessage(String message) {
    return Unirest.post(urlHook)
            .header("Content-Type", "application/json")
            .body(message)
            .asString()
            .getBody();
  }
}
