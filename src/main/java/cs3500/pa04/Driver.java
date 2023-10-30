package cs3500.pa04;

import cs3500.pa04.controller.BattleShipController;
import cs3500.pa04.controller.Controller;
import cs3500.pa04.controller.ProxyController;
import cs3500.pa04.model.player.ComputerPlayer;
import cs3500.pa04.model.player.Player;
import cs3500.pa04.view.BattleShipView;
import cs3500.pa04.view.View;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {

    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    View view = new BattleShipView(output);

    if (args.length == 0) {
      Controller controller = new BattleShipController(input, view);
      controller.playGame();
    } else if (args.length == 2) {
      String host = args[0];
      int port;
      try {
        port = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid port argument");
      }
      Driver.runClient(host, port, view);
    } else {
      throw new IllegalArgumentException("Invalid command line arguments");
    }
  }

  /**
   * Runs the client side of the game.
   *
   * @param host - the host to connect to
   * @param port - the port to connect to
   * @param view - the view to use
   */
  private static void runClient(String host, int port, View view) {
    Controller proxyController;
    Socket socket;
    Player player;
    try {
      socket = new Socket(host, port);
      player = new ComputerPlayer(view);
    } catch (IOException e) {
      throw new IllegalStateException("Could not connect to server");
    }
    proxyController = new ProxyController(socket, player);
    proxyController.playGame();
  }
}


