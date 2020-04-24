package com.hari.animalpuzzle.activity;

import java.util.HashMap;
import java.util.Random;



import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hari.animalpuzzle.FinalPuzzleGame;
import com.hari.animalpuzzle.R;

public class FinalPuzzleGameActivity extends Activity {
	FinalPuzzleGame RocksGame = new FinalPuzzleGame();
	
	public ImageButton[][] buttonArray = new ImageButton[RocksGame.BOARD_ROW][RocksGame.BOARD_COL];
	
	/*Both xVal and yVal are meant to hold the actual x and y 
	 *coordinates of the buttons on the screen
	 */
	private float[][] xVal = new float[RocksGame.BOARD_ROW][RocksGame.BOARD_COL];
	private float[][] yVal = new float[RocksGame.BOARD_ROW][RocksGame.BOARD_COL];
	
	/*tempButtonArray and numArray are used as part of the
	 *shuffling process in order to shuffle the order of the buttons*/
	ImageButton [] tempButtonArray = new ImageButton[RocksGame.BOARD_SIZE];
	int [] numArray = new int[RocksGame.BOARD_SIZE];
	
	/*buttonPressRow and buttonPressCol are meant to map
	 *button ID's to their row and column within the buttonArray*/
	HashMap<Integer,Integer> buttonPressRow = new HashMap<Integer,Integer>();
	HashMap<Integer,Integer> buttonPressCol = new HashMap<Integer,Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initializeButtonArray();
		addImageToPuzzle();
		disableAllButtons();
		
	}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus) {
		/*Both xVal and yVal are initialized here instead of 
		 *onCreate because UI is not drawn yet in onCreate thus
		 *you cannot get the coordinates of buttons*/
		for(int i = 0; i < RocksGame.BOARD_ROW; i++){
			for(int j = 0; j < RocksGame.BOARD_COL; j++){
				xVal[i][j] = buttonArray[i][j].getX();
				yVal[i][j] = buttonArray[i][j].getY();
			}
		}
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void enableAllButtons(){
		for(int i = 0; i < RocksGame.BOARD_ROW; i++ ){
			for(int j = 0; j < RocksGame.BOARD_COL; j++){
				buttonArray[i][j].setEnabled(true);
			}
		}
	}
	public void disableAllButtons(){
		
		for(int i = 0; i < RocksGame.BOARD_ROW; i++ ){
			for(int j = 0; j < RocksGame.BOARD_COL; j++){
				buttonArray[i][j].setEnabled(false);
			}
		}
	}
	
	public void addImageToPuzzle(){
		
		Bitmap bmap = BitmapFactory.decodeResource(getResources(), R.drawable.a0);
		Bitmap bMapScaled = Bitmap.createScaledBitmap(bmap, 750, 750, true);
		
		Bitmap[] bMapArray = new Bitmap[RocksGame.BOARD_SIZE];
		
		/*Here the scaled 750x750 image is being split up into 9
		 *250x250 blocks, these blocks are then set as the image on
		 *the various buttons*/
		
		bMapArray[0] = Bitmap.createBitmap(bMapScaled,0,0,250,250);
		bMapArray[1] = Bitmap.createBitmap(bMapScaled,250,0,250,250);
		bMapArray[2] = Bitmap.createBitmap(bMapScaled,500,0,250,250);
		bMapArray[3] = Bitmap.createBitmap(bMapScaled,0,250,250,250);
		bMapArray[4] = Bitmap.createBitmap(bMapScaled,250,250,250,250);
		bMapArray[5] = Bitmap.createBitmap(bMapScaled,500,250,250,250);
		bMapArray[6] = Bitmap.createBitmap(bMapScaled,0,500,250,250);
		bMapArray[7] = Bitmap.createBitmap(bMapScaled,250,500,250,250);
		bMapArray[8] = Bitmap.createBitmap(bMapScaled,500,500,250,250);
		
		//Leave first button empty and fill out rest of buttons with images
		buttonArray[0][1].setImageBitmap(bMapArray[1]);
		buttonArray[0][2].setImageBitmap(bMapArray[2]);
		buttonArray[1][0].setImageBitmap(bMapArray[3]);
		buttonArray[1][1].setImageBitmap(bMapArray[4]);
		buttonArray[1][2].setImageBitmap(bMapArray[5]);
		buttonArray[2][0].setImageBitmap(bMapArray[6]);
		buttonArray[2][1].setImageBitmap(bMapArray[7]);
		buttonArray[2][2].setImageBitmap(bMapArray[8]);
		
	}
	
	
	public void initializeButtonArray(){
		buttonArray[0][0] = (ImageButton) findViewById(R.id.zero);
		buttonArray[0][1] = (ImageButton) findViewById(R.id.one);
		buttonArray[0][2] = (ImageButton) findViewById(R.id.two);
		buttonArray[1][0] = (ImageButton) findViewById(R.id.three);
		buttonArray[1][1] = (ImageButton) findViewById(R.id.four);
		buttonArray[1][2] = (ImageButton) findViewById(R.id.five);
		buttonArray[2][0] = (ImageButton) findViewById(R.id.six);
		buttonArray[2][1] = (ImageButton) findViewById(R.id.seven);
		buttonArray[2][2] = (ImageButton) findViewById(R.id.eight);
		
		
	}
	public void shuffleButtonClick (View v){
		
		if(v.getId() == R.id.shuffleButton){
			
			//enable the tiles so they can be clicked
			enableAllButtons();
			
			
			initializeButtonArray();
			
			//clear hashmaps denoting relationship between button and location
			buttonPressRow.clear();
			buttonPressCol.clear();
			
			initializeTempArrays();
			
			//shuffle array
			Random rand = new Random();
			for(int i = 0; i < RocksGame.BOARD_SIZE; i++){
				int randomPos = rand.nextInt(RocksGame.BOARD_SIZE);
				int temp = numArray[i];
				ImageButton tempButton = tempButtonArray[i];
				numArray[i] = numArray[randomPos];
				tempButtonArray[i] = tempButtonArray[randomPos];
				numArray[randomPos] = temp;
				tempButtonArray[randomPos] = tempButton;
			}
			
			//set buttons in random order and update UI accordingly
			int tempIndex = 0;
			for(int i = 0; i < RocksGame.BOARD_ROW; i++){
				for(int j = 0; j < RocksGame.BOARD_COL; j++ ){
					RocksGame.mBoard[i][j] = numArray[tempIndex];
					buttonArray[i][j] = tempButtonArray[tempIndex];
					
					if(RocksGame.mBoard[i][j] == 0){
						RocksGame.setEmptySpaceRow(i);
				    	RocksGame.setEmptySpaceCol(j);
					}
					
					tempIndex++;
					
					buttonPressRow.put(buttonArray[i][j].getId(), i);
					buttonPressCol.put(buttonArray[i][j].getId(), j);
					
					buttonArray[i][j].setX(xVal[i][j]);
					buttonArray[i][j].setY(yVal[i][j]);
				}
			}
		}
		
	}
	
	public void checkIfGameOver(){
		if(RocksGame.isComplete()){
			Toast toast = Toast.makeText(this, "YOU WIN!!!", Toast.LENGTH_LONG);
			toast.show();
		}
	}
	
	public void initializeTempArrays(){
		/*initialize both temp arrays with integers/buttons
		 *0 to boardSize*/
		
		for(int i = 0; i < numArray.length; i++){
			numArray[i] = i;
		}

		tempButtonArray[0] = buttonArray[0][0];
		tempButtonArray[1] = buttonArray[0][1];
		tempButtonArray[2] = buttonArray[0][2];
		tempButtonArray[3] = buttonArray[1][0];
		tempButtonArray[4] = buttonArray[1][1];
		tempButtonArray[5] = buttonArray[1][2];
		tempButtonArray[6] = buttonArray[2][0];
		tempButtonArray[7] = buttonArray[2][1];
		tempButtonArray[8] = buttonArray[2][2];
	}
	
	public void updateMap(int id, int row, int col){
		buttonPressRow.put(id, RocksGame.emptySpaceRow);
		buttonPressCol.put(id, RocksGame.emptySpaceCol);
		buttonPressRow.put(R.id.zero, row);
		buttonPressCol.put(R.id.zero, col);
	}
	
	public void UISwap(int row, int col){
		buttonArray[row][col].setX(xVal[RocksGame.emptySpaceRow][RocksGame.emptySpaceCol]);
		buttonArray[row][col].setY(yVal[RocksGame.emptySpaceRow][RocksGame.emptySpaceCol]);
		buttonArray[RocksGame.emptySpaceRow][RocksGame.emptySpaceCol].setX(xVal[row][col]);
		buttonArray[RocksGame.emptySpaceRow][RocksGame.emptySpaceCol].setY(yVal[row][col]);
	}
	
	public void buttonArraySwap(int row, int col){
		ImageButton temp = buttonArray[row][col];
		buttonArray[row][col] = buttonArray[RocksGame.emptySpaceRow][RocksGame.emptySpaceCol];
		buttonArray[RocksGame.emptySpaceRow][RocksGame.emptySpaceCol] = temp;
	}
	
	
	public void ButtonOnClick(View v){
		int buttonRow = 0;
		int buttonCol = 0;
		
		if(v.getId() != R.id.zero){
			buttonRow = buttonPressRow.get(v.getId());
			buttonCol = buttonPressCol.get(v.getId());
			
			if(RocksGame.isAdjacent(buttonRow, buttonCol)){
				
				//update button mappings
				updateMap(v.getId(), buttonRow, buttonCol);
				
				//switch button locations on UI
				UISwap(buttonRow, buttonCol);
				
				//switch button locations in array to maintain synchronization
				buttonArraySwap(buttonRow, buttonCol);
				
				//switch in underlying integer array to maintain synchronization
				RocksGame.setMove(buttonRow, buttonCol);
				RocksGame.setEmptySpaceRow(buttonRow);
				RocksGame.setEmptySpaceCol(buttonCol);
				
				checkIfGameOver();
			}
		}
		
	}	

}
