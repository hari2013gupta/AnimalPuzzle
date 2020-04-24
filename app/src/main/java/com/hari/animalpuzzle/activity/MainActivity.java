package com.hari.animalpuzzle.activity;

//import com.rock.canvasfingerpainting.R;
//
//import com.rock.canvasfingerpainting.RZoneActivity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.hari.animalpuzzle.R;
import com.hari.animalpuzzle.activity.AboutActivity;
import com.hari.animalpuzzle.activity.HelpActivity;
import com.hari.animalpuzzle.activity.ModeActivity;

public class MainActivity extends Activity {
	
//    Button _Button3,_Button4,_Button5;
	ImageButton _btn_play, _btn_help, _btn_about, _btn_exit;
	String getURL,URL,bottomURL;View topAdd,bottomAdd;Handler handler;
//	ImageButton Rzone;
	final int []imageAddArray={R.drawable.banner_ucweb,R.drawable.banner_olx,R.drawable.banner_freeapps,R.drawable.banner_topandroid,R.drawable.banner_dealslelo,R.drawable.banner_flipcart};
	final int []imageBottomArray={R.drawable.banner_freeapps,R.drawable.banner_topandroid,R.drawable.banner_dealslelo,R.drawable.banner_olx,R.drawable.banner_flipcart,R.drawable.banner_ucweb};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mainu);
        
///////////////////hari_banner
        
//----------------------hhari-----------
topAdd=(View)findViewById(R.id.topBanner);

bottomAdd=(View)findViewById(R.id.bottomBanner);

		handler = new Handler();
		
       Runnable runnable = new Runnable() {
         int j=0;
         public void run() {
      	   topAdd.setBackgroundResource(imageAddArray[j]);
             bottomAdd.setBackgroundResource(imageBottomArray[j]);
             	if(j==0){
             		URL="https://play.google.com/store/apps/details?id=com.UCMobile.intl&referrer=utm_source%3Dtnj%2540hariom-CASH";
             		bottomURL="http://freeappstore.in/";
             	}else if(j==1){
             		URL="http://hasoffers.ymtrack.com/aff_c?offer_id=12422&aff_id=14788";
             		bottomURL="http://appmyfriend.com/";
             	}else if(j==2){
             		URL="http://freeappstore.in/";
             		bottomURL="http://www.dealslelo.com/";
             	}else if(j==3){
             		URL="http://appmyfriend.com/";
             		bottomURL="http://hasoffers.ymtrack.com/aff_c?offer_id=12422&aff_id=14788";
         		}else if(j==4){
         			URL="http://www.dealslelo.com/";
         			bottomURL="http://yeahmobi.go2cloud.org/aff_c?offer_id=15692&aff_id=14788";
         			
         		}else if(j==5){
         			URL="http://yeahmobi.go2cloud.org/aff_c?offer_id=15692&aff_id=14788";
               		bottomURL="https://play.google.com/store/apps/details?id=com.UCMobile.intl&referrer=utm_source%3Dtnj%2540hariom-CASH";
         			
           	}
          	j++;
          	if(j>imageBottomArray.length-1){
          	
          		j=0;
          	}
              handler.postDelayed(this, 6000);  //for interval...
          	System.out.println("--------j----//"+j);
          }
      };	            	
      handler.postDelayed(runnable, 500); //for initial delay..
//_________harisep
      
      topAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Context context=v.getContext();
      		Intent thingyToInstall=new Intent(Intent.ACTION_VIEW);
      		thingyToInstall.setDataAndType(Uri.parse(URL), null);
      		context.startActivity(thingyToInstall);
			}
		});
      
    bottomAdd.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Context context=v.getContext();
   		Intent thingyToInstall=new Intent(Intent.ACTION_VIEW);
   		thingyToInstall.setDataAndType(Uri.parse(bottomURL), null);
   		context.startActivity(thingyToInstall);
		}
	});

    _btn_play = (ImageButton)findViewById(R.id.btn_play);
    _btn_play.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
//			shareMethod();
    		      Intent intent=new Intent(getApplicationContext(), ModeActivity.class);
		      startActivity(intent);
			}
    	});

    _btn_help = (ImageButton)findViewById(R.id.btn_help);
    _btn_help.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
		      startActivity(intent);
			}
    	});

    _btn_about = (ImageButton)findViewById(R.id.btn_about);
    _btn_about.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intent=new Intent(getApplicationContext(), AboutActivity.class);
		      startActivity(intent);
			}
    	});

    _btn_exit = (ImageButton)findViewById(R.id.btn_exit);
    _btn_exit.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			showAlert("Dear Users, If you like our app please give us Rating of 5 stars.");

		}
    });
//  	----------------------hhari-----------
  	        //for initial delay..

//////////////endbanner
//    _Button3 = (Button)findViewById(R.id.Button3x3);
//
//    _Button3.setOnClickListener(new OnClickListener() {
//  		
//  		@Override
//  		public void onClick(View v) {
//
//  			Intent Activity3 = new Intent(getApplicationContext(), 
//
//  					MainActivity3.class);
//  		      startActivity(Activity3);
//  		}
//
//    });
//
//    
//    _Button4 = (Button)findViewById(R.id.Button4x4);
//    
//    _Button4.setOnClickListener(new OnClickListener() {
//  		
//  		@Override
//  		public void onClick(View v) {
//  		    
//  			Intent Activity4 = new Intent(getApplicationContext(), 
//
//  					MainActivity4.class);
//  		      startActivity(Activity4);
//  		}
//  	
//    });
//
//    _Button5 = (Button)findViewById(R.id.Button5x5);
//    
//    
//    _Button5.setOnClickListener(new OnClickListener() {
//  		
//  		@Override
//  		public void onClick(View v) {
//  	
//  			Intent Activity5 = new Intent(getApplicationContext(), 
//
//  					MainActivity5.class);
//  		      startActivity(Activity5);
//  		}
//  	
//    });

////////hari_ex////////////////////////////
    
    }
    //hariJ_all_functiona=s
	public void showAlert(String msg){
    	AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Animal Puzzle");
    	builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
        		getURL="https://play.google.com/store/apps/details?id=com.rocks.rockssplittingfinal";
//        		Intent intent=new Intent(getApplicationContext(), getURL);
//    			startActivity(intent);  

        		Intent browserIntent = new Intent(
        				Intent.ACTION_VIEW, Uri.parse(getURL));
        		startActivity(browserIntent);
//        		System.out.println("----------------exti---------------");//UC wb
//        		Context context=view.getContext();
//        		Intent thingyToInstall=new Intent(Intent.ACTION_VIEW);
//        		thingyToInstall.setDataAndType(Uri.parse(getURL), null);
//        		context.startActivity(thingyToInstall);
            }
        });
        
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
//                dialog.cancel();
                dialog.cancel();
//                System.runFinalizersOnExit(true);
//                System.exit(0);finish();
		        Intent intent = new Intent(Intent.ACTION_MAIN);
		        intent.addCategory(Intent.CATEGORY_HOME);
		        
		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        startActivity(intent);
            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

/////////////////////////////end_exit=onBackpressed
	 @Override
	 public void onBackPressed() {
			showAlert("Dear Users, If you like our app please give us Rating of 5 stars.");
//			int backButtonCount = 0;
//			if(backButtonCount >= 1)
//		    {
//			     super.onBackPressed();
//			     overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
//		        Intent intent = new Intent(Intent.ACTION_MAIN);
//		        intent.addCategory(Intent.CATEGORY_HOME);
//		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		        startActivity(intent);
//		    }
//		    else
//		    {
//		        Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show();
//		        backButtonCount++;
//		    }

	 }
}
