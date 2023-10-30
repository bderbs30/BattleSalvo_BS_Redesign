package cs3500.pa04.model.mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.List;

/**
 * Mock a Socket to simulate behaviors of ProxyControllers being connected to a server.
 * Specifically, this mock will throw an IOException when getInputStream() or getOutputStream()
 */
public class MocketFail extends Socket {


  private final InputStream testInputs;
  private final ByteArrayOutputStream testLog;

  /**
   * @param testLog what the server has received from the client
   * @param toSend  what the server will send to the client
   */
  public MocketFail(ByteArrayOutputStream testLog, List<String> toSend) {
    this.testLog = testLog;

    // Set up the list of inputs as separate messages of JSON for the ProxyDealer to handle
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    for (String message : toSend) {
      printWriter.println(message);
    }
    this.testInputs = new ByteArrayInputStream(stringWriter.toString().getBytes());
  }

  @Override
  public InputStream getInputStream() throws IOException {
    throwInOut();
    return null;
  }

  @Override
  public OutputStream getOutputStream() throws IOException {
    throwInOut();
    return null;
  }

  private void throwInOut() throws IOException {
    throw new IOException("Mock throwing an error");
  }
}


