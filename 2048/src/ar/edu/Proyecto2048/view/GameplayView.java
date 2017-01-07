package ar.edu.Proyecto2048.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import ar.edu.Proyecto2048.controller.GameplayController;
import ar.edu.Proyecto2048.controller.ReplayController;
import ar.edu.Proyecto2048.util.Constants;
import ar.edu.Proyecto2048.util.Direction;
import ar.edu.Proyecto2048.util.EndGameDimmerListener;
import ar.edu.Proyecto2048.util.PlayerState;
import ar.edu.Proyecto2048.util.ReplayActionListener;
import ar.edu.Proyecto2048.util.Resources;


public class GameplayView extends JPanel {
	
	private static final long serialVersionUID = 4146227121703610809L;
	private NorthComponents northComponents;
	private CenterComponents centerComponents;
	private Integer condition;
	private InputMap inputMap;
	private ActionMap actionMap;
	private Boolean replayFlag = Boolean.FALSE;
	private Integer index = 0;
	Timer timer = new Timer(500, null);
	
	public GameplayView(String gameMode){
		switch(gameMode){
		case "new game":
			GameplayController.play();
			break;
		case "replay":
			ReplayController.setTimer(new ReplayActionListener());
			GameplayController.replay();
			replayFlag = Boolean.TRUE;
			break;
		default:
			System.out.println("get out");
			break;
		}
		this.setNorthComponents(new NorthComponents());
		this.setCenterComponents(new CenterComponents());
		this.initAesthetics();
		this.setKeyBindings();
	    this.add(Box.createVerticalGlue());
	    this.add(getNorthComponents(), BorderLayout.NORTH);
	    this.add(getCenterComponents(), BorderLayout.CENTER);
	    this.add(Box.createHorizontalGlue());
	    if(replayFlag) this.replay();
	}
	
	private void initAesthetics() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBounds(0, 0, 800, 600);
		this.setOpaque(Boolean.FALSE);
	    this.getCenterComponents().getBoard().renderActiveTiles();
	}
	
	private void setKeyBindings() {
		this.condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		this.inputMap = getInputMap(condition);
	    this.actionMap = getActionMap();
	    this.initializeBoardActionMap();
	}
	

	public NorthComponents getNorthComponents() {
		return northComponents;
	}


	public void setNorthComponents(NorthComponents northComponents) {
		this.northComponents = northComponents;
	}


	public CenterComponents getCenterComponents() {
		return centerComponents;
	}


	public void setCenterComponents(CenterComponents centerComponents) {
		this.centerComponents = centerComponents;
	}
	
	public BoardView getBoard() {
		return getCenterComponents().getBoard();
	}

	public HeaderView getHeader() {
		return getNorthComponents().getHeader();
	}
	
	public void replay() {
		this.initTimer();
		timer.start();
	}
	
	public void initTimer() {
		this.timer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getBoard().renderActiveTiles();
				gameplayStateSwitch(GameplayController.checkGameplayState());
				getHeader().displayCurrentPlayer();
				index++;
				stopCheck();
			}
			
		});
	}
	
	public void stopCheck() {
		if(index.equals(ReplayController.getGameplayReplay().getMovements().size())){
			timer.stop();
			this.index = 0;
		}
	}

/** Arrowkey action mapping and board actions **/
	
	private void initializeBoardActionMap(){
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), Direction.UP);
	    actionMap.put(Direction.UP, new AbstractAction() {
	    	
			private static final long serialVersionUID = 1675767541639063579L;

			public void actionPerformed(ActionEvent arg0) {
				GameplayController.boardMover(Direction.UP);
				ReplayController.addMovement(Direction.UP);
				getBoard().renderActiveTiles();
				gameplayStateSwitch(GameplayController.checkGameplayState());
				getHeader().displayCurrentPlayer();
		     }
		  });
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), Direction.DOWN);
	    actionMap.put(Direction.DOWN, new AbstractAction() {

			private static final long serialVersionUID = -6193194948080612160L;

			public void actionPerformed(ActionEvent arg0) {
				GameplayController.boardMover(Direction.DOWN);
				ReplayController.addMovement(Direction.DOWN);
				getBoard().renderActiveTiles();
				gameplayStateSwitch(GameplayController.checkGameplayState());
				getHeader().displayCurrentPlayer();

	    	}
	    });
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), Direction.LEFT);
	    actionMap.put(Direction.LEFT, new AbstractAction() {

			private static final long serialVersionUID = -6421907011268058271L;

			public void actionPerformed(ActionEvent arg0) {
				GameplayController.boardMover(Direction.LEFT);
				ReplayController.addMovement(Direction.LEFT);
				getBoard().renderActiveTiles();
				gameplayStateSwitch(GameplayController.checkGameplayState());
				getHeader().displayCurrentPlayer();

	    	}
	    });
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), Direction.RIGHT);
	    actionMap.put(Direction.RIGHT, new AbstractAction() {

			private static final long serialVersionUID = -6479495729574569907L;

			public void actionPerformed(ActionEvent arg0) {
				GameplayController.boardMover(Direction.RIGHT);
				ReplayController.addMovement(Direction.RIGHT);
				getBoard().renderActiveTiles();
				gameplayStateSwitch(GameplayController.checkGameplayState());
				getHeader().displayCurrentPlayer();

	    	}
	    });
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "closeWindow");
	    actionMap.put("closeWindow", new AbstractAction() {
	    	
	    	private static final long serialVersionUID = -6479495729574569907L;
	    	
	    	public void actionPerformed(ActionEvent arg0) {
	    		ReplayController.stopTimer();
	    		timer.stop();
	    		MainView.getMainLayeredPane().swapFrontPanel(new MainMenuView());
	    	}
	    });
	}
	
	private void gameplayStateSwitch(PlayerState s){
		switch(s){
		case LOSER:
			MainView.getMainLayeredPane().addDimmer(new AlphaContainer(new DimmerView("loser", new EndGameDimmerListener())));
			break;
		case PLAYING:
			GameplayController.switchPlayer();
			break;
		case TIE:
			break;
		case WINNER:
			MainView.getMainLayeredPane().addDimmer(new AlphaContainer(new DimmerView("winner", new EndGameDimmerListener())));
			break;
		default:
			System.out.println("Invalid Player State");
			break;
		}
	}
	
	private class CenterComponents extends JPanel{
		
		private static final long serialVersionUID = 3424848772759818143L;
		private BoardView board;

		public CenterComponents(){
			this.setBoard(new BoardView());
			this.setAlignmentY(CENTER_ALIGNMENT);
			this.setOpaque(Boolean.FALSE);
			this.add(this.getBoard(), BorderLayout.CENTER);
		}

		private BoardView getBoard() {
			return board;
		}

		private void setBoard(BoardView board) {
			this.board = board;
		}
	}
	
	private class NorthComponents extends JPanel{

		private static final long serialVersionUID = 2427384937564171555L;
		private HeaderView header;
		
		public NorthComponents(){
			this.setHeader(new HeaderView());
			this.setAlignmentY(CENTER_ALIGNMENT);
			this.setOpaque(Boolean.FALSE);
			this.add(getHeader(), BorderLayout.CENTER);
		}
		
		private HeaderView getHeader() {
			return header;
		}

		private void setHeader(HeaderView header) {
			this.header = header;
		}

	}

	private class BoardView extends JPanel{

		private static final long serialVersionUID = -1852837983810518489L;
		private Integer borderWidth;
		private TileContainer[][] tileHolder;

		public BoardView() {
			this.setBorderWidth(3);
			this.setTileHolder(new TileContainer[Constants.BOARD_SIZE][Constants.BOARD_SIZE]);
			this.setLayout(new GridLayout(Constants.BOARD_SIZE,Constants.BOARD_SIZE));
			this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			this.prepareTileHolder();
		}
	
		public Integer getBorderWidth() {
			return borderWidth;
		}

		public void setBorderWidth(Integer borderWidth) {
			this.borderWidth = borderWidth;
		}

		public TileContainer[][] getTileHolder() {
			return tileHolder;
		}

		public void setTileHolder(TileContainer[][] tileHolder) {
			this.tileHolder = tileHolder;
		}
		
		//** Board generator **//
		
		private void prepareTileHolder() {
			for (int row = 0; row < Constants.BOARD_SIZE; row++) {
				for (int col = 0; col < Constants.BOARD_SIZE; col++) {
					this.getTileHolder()[row][col] = new TileContainer(this.getBorderWidth());
					this.add(getTileHolder()[row][col]);
				}
			}
		}
		
		private void clearTileHolder() {
			for (int row = 0; row < Constants.BOARD_SIZE; row++) {
				for (int col = 0; col < Constants.BOARD_SIZE; col++) {
					if(!this.getTileHolder()[row][col].isEmpty()) this.getTileHolder()[row][col].removeAll();
				}
			}
		}
				
		protected void renderActiveTiles() {
			this.clearTileHolder();
			for(int i = 0; i < Constants.BOARD_SIZE; i++)
				for(int j = 0; j < Constants.BOARD_SIZE; j++)
					if(GameplayController.MAIN_BOARD.getBoard()[i][j] != null)
						this.getTileHolder()[i][j].add(new TileView(GameplayController.MAIN_BOARD.getBoard()[i][j].getValue()));
			repaint();
			revalidate();
		}
		
		//** ...what the board is made out of **/
		
		private class TileView extends JLabel {
			
			private static final long serialVersionUID = -7323073623306586982L;

			public TileView(Integer value){
				super("",SwingConstants.CENTER);
				this.setText(value.toString());
				this.fontSwitch(this.getNumberOfDigits(value));
				this.setForeground(Color.BLACK);
				this.setPreferredSize(new Dimension(64,64));
				this.setMaximumSize(new Dimension(64,64));
				this.setMinimumSize(new Dimension(64,64));
				this.setBackground(Constants.BOARD_COLORS[Constants.TILE_COLOR]);
				this.setOpaque(Boolean.TRUE);			
			}
			
			private Integer getNumberOfDigits(Integer num) {
				return (int) Math.log10(num) + 1;
			}
			
			private void fontSwitch(Integer digits) {
				switch(digits){
				case 3:
					this.setFont(Resources.THREE_DIGIT_FONT);
					break;
				case 4:
					this.setFont(Resources.FOUR_DIGIT_FONT);
					break;
				default:
					this.setFont(Resources.FONT);
					break;
				}
			}
			
		}
		
		private class TileContainer extends JPanel {
			
			private static final long serialVersionUID = -553762472008722325L;

			public TileContainer(Integer borderWidth) {
				this.setLayout(new BorderLayout());
				this.setPreferredSize(new Dimension(64,64));
				this.setMaximumSize(new Dimension(64,64));
				this.setMinimumSize(new Dimension(64,64));
				this.setBackground(Constants.BOARD_COLORS[Constants.BOARD_COLOR]);
				this.setBorder(BorderFactory.createMatteBorder(borderWidth, 
						borderWidth, 
						borderWidth, 
						borderWidth, 
						Color.BLACK));	
			}
			
			public Boolean isEmpty() {
				return this.getComponents().length == 0 ? Boolean.TRUE : Boolean.FALSE;
			}
			
		}

	}
	
	//** Board's partner in crime **//
	
	private class HeaderView extends JPanel{
		
		private static final long serialVersionUID = 1L;
		private JLabel score;
		private JLabel currentPlayer;
		private JLabel nMoves;
		
		public HeaderView ()
		{
			this.setLayout(new FlowLayout());
			this.initHeaderElements();
			this.add(getCurrentPlayer());
			this.add(getScore());
			this.add(getnMoves());
			this.setOpaque(Boolean.FALSE);
		}
		
		private void initHeaderElements() {
			this.setScore(new JLabel("", SwingConstants.CENTER));
			this.setCurrentPlayer(new JLabel("", SwingConstants.CENTER));
			this.setnMoves(new JLabel("", SwingConstants.CENTER));
			this.getCurrentPlayer().setText("Player " + (GameplayController.currentPlayer.getId()+1));
			this.getScore().setText("Score " + GameplayController.currentPlayer.getScore());
			this.getnMoves().setText("Moves " + GameplayController.currentPlayer.getnMoves());
			this.getScore().setFont(Resources.FONT);
			this.getCurrentPlayer().setFont(Resources.FONT);
			this.getnMoves().setFont(Resources.FONT);
			this.getScore().setForeground(Constants.FONT_COLOR);
			this.getCurrentPlayer().setForeground(Constants.FONT_COLOR);
			this.getnMoves().setForeground(Constants.FONT_COLOR);
			this.getScore().setPreferredSize (new Dimension (200, 30));
			this.getCurrentPlayer().setMinimumSize (new Dimension (200, 30));
			this.getnMoves().setMaximumSize (new Dimension (200, 30));
			this.getScore().setPreferredSize (new Dimension (200, 30));
			this.getCurrentPlayer().setMinimumSize (new Dimension (200, 30));
			this.getnMoves().setMaximumSize (new Dimension (200, 30));
			this.getScore().setPreferredSize (new Dimension (200, 30));
			this.getCurrentPlayer().setMinimumSize (new Dimension (200, 30));
			this.getnMoves().setMaximumSize (new Dimension (200, 30));
			this.getScore().setOpaque(Boolean.FALSE);
			this.getCurrentPlayer().setOpaque(Boolean.FALSE);
			this.getnMoves().setOpaque(Boolean.FALSE);
		}

		private JLabel getScore() {
			return score;
		}

		private void setScore(JLabel score) {
			this.score = score;
		}

		private JLabel getCurrentPlayer() {
			return currentPlayer;
		}

		private void setCurrentPlayer(JLabel currentPlayer) {
			this.currentPlayer = currentPlayer;
		}

		private JLabel getnMoves() {
			return nMoves;
		}

		private void setnMoves(JLabel nMoves) {
			this.nMoves = nMoves;
		}
		
		public void displayCurrentPlayer() {
			this.getCurrentPlayer().setText("Player " + (GameplayController.currentPlayer.getId()+1));
			this.getScore().setText("Score " + GameplayController.currentPlayer.getScore());
			this.getnMoves().setText("Moves " + GameplayController.currentPlayer.getnMoves());
		}

	}

}