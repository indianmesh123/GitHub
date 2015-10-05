package com.sayhello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sayhello.adapter.ActivityAdapter;
import com.sayhello.gettersetter.ActivityList;
import com.sayhello.gettersetter.GetActivityList;
import com.sayhello.gettersetter.MyActivityList;
import com.sayhello.internetcheck.ConnectionDetector;

@SuppressWarnings("deprecation")
public class EditActivity extends Activity 
{
	GridView gridActivity,gridActivity1;
	static String imageName[]={"Coffee","Chatting","Indoor Sports","Cycling etc","Adventure Sports","Outdoor Sports","Events","Travel","Water Activities","Dog Walking","Playing Music","Camping","Gym","Video Games","Other","Walking","Cooking",
		"Dancing","Bars,Dining","Reading"};
	boolean isShowing;
	String phoneNumber;
	GPSTracker gps;	
	static int hourFromValue;
	static int hourToValue;
	static int minToValue;
	public static ArrayList<String> arrGetType=null;
	static int minFromValue;
	static String startTime;
	static String endTime;
	static String valid;
	static String getDescription;
	static String gettype;
	static String getstartTime;
	static String getendTime;
	static String getdays;
	static boolean isAct1=false;
	static boolean isAct2=false;
	static boolean isAct3=false;
	static boolean isAct4=false;
	static boolean isAct5=false;
	static boolean isAct6=false;
	static boolean isActivity1=false;
	static boolean isActivity2=false;
	static boolean isActivity3=false;
	static boolean isActivity4=false;
	static boolean isActivity5=false;
	static boolean isActivity6=false;
	static boolean isBitmap1=false;
	static boolean isBitmap2=false;
	static boolean isBitmap3=false;
	static boolean isBitmap4=false;
	static boolean isBitmap5=false;
	static boolean isBitmap6=false;
	static int firstActivity=1;
	boolean firstAgain=false;
	boolean secondAgain=false;
	boolean thirdAgain=false;
	boolean fourthAgain=false;
	boolean fifthAgain=false;
	boolean sixthAgain=false;
	static ArrayList<String> arrList1=null;
	static ArrayList<String> arrList2=null;
	static ArrayList<String> arrList3=null;
	static ArrayList<String> arrList4=null;
	static ArrayList<String> arrList5=null;
	static ArrayList<String> arrList6=null;
	static ArrayList<String> arrDay1=null;
	static ArrayList<String> arrDay2=null;
	static ArrayList<String> arrDay3=null;
	static ArrayList<String> arrDay4=null;
	static ArrayList<String> arrDay5=null;
	static ArrayList<String> arrDay6=null;
	HashMap<String,ArrayList<MyActivityList>> hmap1=null;
	HashMap<String,ArrayList<MyActivityList>> hmap2=null;
	HashMap<String,ArrayList<MyActivityList>> hmap3=null;
	HashMap<String,ArrayList<MyActivityList>> hmap4=null;
	HashMap<String,ArrayList<MyActivityList>> hmap5=null;
	HashMap<String,ArrayList<MyActivityList>> hmap6=null;
	static int id1=0;
	static HashMap<String,ArrayList<ActivityList>> hmap=new HashMap<String, ArrayList<ActivityList>>();
	static ArrayList<Bitmap> arrBitmap=null;
	static ArrayList<GetActivityList> arrGetList=null;
	static ArrayList<String> arrGetDays=null;
	int i=1;
	boolean isSelected=false;
	static ArrayList<ActivityList> arrActivityList1=null;
	static ArrayList<ActivityList> arrActivityList2=null;
	static ArrayList<ActivityList> arrActivityList3=null;
	static ArrayList<ActivityList> arrActivityList4=null;
	static ArrayList<ActivityList> arrActivityList5=null;
	static ArrayList<ActivityList> arrActivityList6=null;

	ImageView imgSide,imgView1,imgView2,imgView3,imgView4,imgView5,imgView6,imgArrow1,imgArrow2,imgArrow3,imgArrow4,imgArrow5,imgArrow6;
	TextView textActivity1,textActivity2,textActivity3,textActivity4,textActivity5,textActivity6;
	static int imageActivity[]={R.drawable.icon_coffee,R.drawable.ico_chatting,R.drawable.ico_indoor_games,R.drawable.ico_outdoor,R.drawable.ico_adventure_sports,R.drawable.ico_outdoor_games,
		R.drawable.ico_movie_tickets,R.drawable.ico_travel,R.drawable.ico_water_activities,R.drawable.ico_dog_walking,R.drawable.ico_play_music,R.drawable.ico_camping,
		R.drawable.ico_gym,R.drawable.ico_video_games,R.drawable.ico_other,R.drawable.ico_walking,R.drawable.ico_cooking,R.drawable.ico_dancing,R.drawable.ico_bars_dining,R.drawable.ico_reading			
	};	
	static JSONObject jsonObject = null;static JSONObject jsonObject1 = null;static JSONObject jsonObject2 = null;static JSONObject jsonObject3 = null;
	static JSONObject jsonObject4= null;static JSONObject jsonObject5 = null;
	Boolean isInternetPresent = false;
	ConnectionDetector cd;
	static ArrayList<String> arrDays=null;
	static JSONArray array=null;
	ImageView imgCheck;
	static boolean isActivated1=false;
	static boolean isActivated2=false;
	static boolean isActivated3=false;
	static boolean isActivated4=false;
	static boolean isActivated5=false;
	static boolean isActivated6=false;
	static boolean isActivated7=false;
	static boolean isActivated8=false;
	boolean isActivated9=false;
	public static final String wurl ="https://api-dev.zyngme.net/sayhello/sayhelloservice/v1/user/activity";
	LinearLayout lTime1,lTime3,lTime4,lTime6,lTime7,lTime9,lTime10,lTime12;
	LinearLayout lEditActivity2,lEditActivity4,lEditActivity5;
	static LinearLayout lEditActivity6;
	static LinearLayout lEditActivity7;
	static LinearLayout lEditActivity8;
	static LinearLayout lEditActivity9;
	LinearLayout lEditActivity24;
	static LinearLayout lEditActivity10;
	static LinearLayout lEditActivity11;
	static LinearLayout lEditActivity12;
	LinearLayout lEditActivity22;
	static double latitude;
	static double longitude;
	static String description;
	static EditText editDescribe; 
	static EditText textZipCode;
	static String activityType;
	static int hourFrom=10;
	static int minFrom=20;
	static int hourTo=12;
	ImageView imgCancel;
	static String strZipCode;
	static int minTo=35;
	static String strId;
	static String selectDay;
	static ActivityList aList=null;
	ImageView imgUpHour,imgDownHour,imgUpMin,imgDownMin,imgUpHour1,imgDownHour1,imgUpMin1,imgDownMin1;
	static TextView textHour;
	static TextView textMin;
	static TextView textMin1;
	static TextView textHour1;
	RelativeLayout lEditActivity;
	Button btnSubmit;
	static Bitmap bitmap;
	static Bitmap bitmap1;
	static Bitmap bitmap2;
	static Bitmap bitmap3;
	static Bitmap bitmap4;
	static Bitmap bitmap5;
	String imageSide;
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);		
		setContentView(R.layout.activity);
		arrDays=new ArrayList<String>();
		arrActivityList1=new ArrayList<ActivityList>();
		arrActivityList2=new ArrayList<ActivityList>();
		arrActivityList3=new ArrayList<ActivityList>();
		arrActivityList4=new ArrayList<ActivityList>();
		arrActivityList5=new ArrayList<ActivityList>();
		arrActivityList6=new ArrayList<ActivityList>();

		lEditActivity24=(LinearLayout)findViewById(R.id.lEditActivity24);
		lEditActivity2=(LinearLayout)findViewById(R.id.lEditActivity2);
		lEditActivity4=(LinearLayout)findViewById(R.id.lEditActivity4);
		lEditActivity5=(LinearLayout)findViewById(R.id.lEditActivity5);
		lEditActivity6=(LinearLayout)findViewById(R.id.lEditActivity6);
		lEditActivity7=(LinearLayout)findViewById(R.id.lEditActivity7);
		lEditActivity8=(LinearLayout)findViewById(R.id.lEditActivity8);
		lEditActivity9=(LinearLayout)findViewById(R.id.lEditActivity9);
		lEditActivity10=(LinearLayout)findViewById(R.id.lEditActivity10);
		lEditActivity11=(LinearLayout)findViewById(R.id.lEditActivity11);
		lEditActivity12=(LinearLayout)findViewById(R.id.lEditActivity12);
		lEditActivity22=(LinearLayout)findViewById(R.id.lEditActivity22);
		btnSubmit=(Button)findViewById(R.id.btnSubmit);
		lTime1=(LinearLayout)findViewById(R.id.lTime1);
		lTime3=(LinearLayout)findViewById(R.id.lTime3);
		lTime4=(LinearLayout)findViewById(R.id.lTime4);
		lTime6=(LinearLayout)findViewById(R.id.lTime6);
		lTime7=(LinearLayout)findViewById(R.id.lTime7);
		lTime9=(LinearLayout)findViewById(R.id.lTime9);
		lTime10=(LinearLayout)findViewById(R.id.lTime10);
		lTime12=(LinearLayout)findViewById(R.id.lTime12);		
		editDescribe=(EditText)findViewById(R.id.editDescribe);
		imgView1=(ImageView)findViewById(R.id.imgView3);
		imgView2=(ImageView)findViewById(R.id.imgView5);
		imgView3=(ImageView)findViewById(R.id.imgView7);
		imgView4=(ImageView)findViewById(R.id.img1);
		imgView5=(ImageView)findViewById(R.id.img3);
		imgView6=(ImageView)findViewById(R.id.img5);
		imgArrow1=(ImageView)findViewById(R.id.imgView4);
		imgArrow2=(ImageView)findViewById(R.id.imgView6);
		imgArrow3=(ImageView)findViewById(R.id.imgView8);
		imgArrow4=(ImageView)findViewById(R.id.img2);
		imgArrow5=(ImageView)findViewById(R.id.img4);
		imgArrow6=(ImageView)findViewById(R.id.img6);
		imgUpHour=(ImageView)findViewById(R.id.imgUpHour);
		imgCancel=(ImageView)findViewById(R.id.imgCancel);
		imgCheck=(ImageView)findViewById(R.id.imgCheck);
		imgSide=(ImageView)findViewById(R.id.imgSide1);
		imgDownHour=(ImageView)findViewById(R.id.imgDownHour);
		imgUpMin=(ImageView)findViewById(R.id.imgUpMin);
		imgDownMin=(ImageView)findViewById(R.id.imgDownMin);
		imgUpHour1=(ImageView)findViewById(R.id.imgUpHour1);
		imgDownHour1=(ImageView)findViewById(R.id.imgDownHour1);
		imgUpMin1=(ImageView)findViewById(R.id.imgUpMin1);
		imgDownMin1=(ImageView)findViewById(R.id.imgDownMin1);
		textHour=(TextView)findViewById(R.id.textHour);
		textMin=(TextView)findViewById(R.id.textMin);
		textHour1=(TextView)findViewById(R.id.textHour1);
		textMin1=(TextView)findViewById(R.id.textMin1);
		textZipCode=(EditText)findViewById(R.id.textZipCode);
		arrList1=new ArrayList<String>();
		arrList2=new ArrayList<String>();
		arrList3=new ArrayList<String>();
		arrList4=new ArrayList<String>();
		arrList5=new ArrayList<String>();
		arrList6=new ArrayList<String>();
		arrDay1=new ArrayList<String>();
		arrDay2=new ArrayList<String>();
		arrDay3=new ArrayList<String>();
		arrDay4=new ArrayList<String>();
		arrDay5=new ArrayList<String>();
		arrDay6=new ArrayList<String>();
		SharedPreferences settings = getSharedPreferences("MyPref",0);
		strId=settings.getString("id","");
		imageSide=settings.getString("image", "");
		Bitmap bitmapIntent=decodeBase64(imageSide);
		arrBitmap=new ArrayList<Bitmap>();
		imgSide.setImageBitmap(bitmapIntent);
		cd = new ConnectionDetector(getApplicationContext());
		gps=new GPSTracker(getApplicationContext());	
		array= new JSONArray();		
		imgCancel.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v) 
			{
				finish();
				moveTaskToBack(true);
			}
		});		
		textZipCode.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				lEditActivity24.setBackgroundColor(Color.parseColor("#BD8D0F"));				
			}
		});
		lEditActivity24.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if(isActivated9==false)
				{
					lEditActivity24.setBackgroundColor(Color.parseColor("#BD8D0F"));
					isActivated9=true;
				}
				else
				{
					lEditActivity24.setBackgroundColor(Color.parseColor("#DCA50D"));
					isActivated9=false;
				}			
			}
		});
		lEditActivity6.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if(isActivated1==false)
				{
					lEditActivity6.setBackgroundColor(Color.parseColor("#2F978C"));
					selectDay="Monday";
					isActivated1=true;
				}
				else
				{
					isActivated1=false;
					lEditActivity6.setBackgroundColor(Color.parseColor("#40B8AC"));
				}
			}
		});
		lEditActivity7.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if(isActivated2==false)
				{
					lEditActivity7.setBackgroundColor(Color.parseColor("#2F978C"));
					selectDay=selectDay+","+"Tuesday";
					isActivated2=true;
				}
				else
				{
					isActivated2=false;
					lEditActivity7.setBackgroundColor(Color.parseColor("#40B8AC"));
				}
			}
		});
		lEditActivity8.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				if(isActivated3==false)
				{
					lEditActivity8.setBackgroundColor(Color.parseColor("#2F978C"));
					selectDay=selectDay+","+"Wednesday";
					isActivated3=true;
				}
				else
				{
					isActivated3=false;
					lEditActivity8.setBackgroundColor(Color.parseColor("#40B8AC"));
				}
			}
		});
		lEditActivity9.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if(isActivated4==false)
				{
					lEditActivity9.setBackgroundColor(Color.parseColor("#2F978C"));
					selectDay=selectDay+","+"Thursday";
					isActivated4=true;
				}
				else
				{
					isActivated4=false;
					lEditActivity9.setBackgroundColor(Color.parseColor("#40B8AC"));
				}
			}
		});
		lEditActivity10.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				if(isActivated5==false)
				{
					lEditActivity10.setBackgroundColor(Color.parseColor("#2F978C"));
					selectDay=selectDay+","+"Friday";
					isActivated5=true;
				}
				else
				{
					isActivated5=false;
					lEditActivity10.setBackgroundColor(Color.parseColor("#40B8AC"));
				}
			}
		});
		lEditActivity11.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				if(isActivated6==false)
				{
					lEditActivity11.setBackgroundColor(Color.parseColor("#2F978C"));
					selectDay=selectDay+","+"Saturday";
					isActivated6=true;
				}
				else
				{
					isActivated6=false;
					lEditActivity11.setBackgroundColor(Color.parseColor("#40B8AC"));
				}

			}
		});
		lEditActivity12.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				if(isActivated7==false)
				{
					lEditActivity12.setBackgroundColor(Color.parseColor("#2F978C"));
					selectDay=selectDay+","+"Sunday";
					isActivated7=true;
				}
				else
				{
					isActivated7=false;
					lEditActivity12.setBackgroundColor(Color.parseColor("#40B8AC"));
				}
			}
		});
		lEditActivity22.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				if(gps.canGetLocation())
				{
					if(isActivated8==false)
					{
						latitude = gps.getLatitude();
						longitude = gps.getLongitude();  
						lEditActivity22.setBackgroundColor(Color.parseColor("#C8892A"));
						isActivated8=true;
					}
					else
					{
						lEditActivity22.setBackgroundColor(Color.parseColor("#DCA50D"));
						isActivated8=false;
					}
				}				
				else
				{           
					gps.showSettingsAlert(EditActivity.this);
				}			
			}
		});
		imgCheck.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{				
				if(isSelected==true)
				{
				new HttpAsyncTask().execute(wurl);		
				}
				else
				{
					final Dialog dialog=new Dialog(EditActivity.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);			
					dialog.setContentView(R.layout.selectactivity);
					Button btnOk=(Button)dialog.findViewById(R.id.btnOk1);

					btnOk.setOnClickListener(new OnClickListener() 
					{					
						@Override
						public void onClick(View arg0) 
						{
							dialog.cancel();
						}
					});

					dialog.show();
				}
			}
		});
		lTime1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				hourFrom--;
				textHour.setText(hourFrom+"");
				if(hourFrom==0)
				{
					lTime1.setEnabled(false);
				}
			}
		});
		lTime3.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				hourFrom++;
				textHour.setText(hourFrom+"");
				if(hourFrom==23)
				{
					lTime3.setEnabled(false);
				}
			}
		});
		lTime6.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				minFrom++;
				textMin.setText(minFrom+"");
				if(minFrom==59)
				{
					lTime6.setEnabled(false);
				}
			}
		});

		lTime9.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				hourTo++;
				textHour1.setText(hourTo+"");
				if(hourTo==23)
				{
					lTime9.setEnabled(false);
				}
			}
		});
		lTime12.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				minTo++;
				textMin1.setText(minTo+"");
				if(minTo==59)
				{
					lTime12.setEnabled(false);
				}
			}
		});
		lTime4.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				minFrom--;
				textMin.setText(minFrom+"");
				if(minFrom==0)
				{
					lTime4.setEnabled(false);
				}
			}
		});
		lTime10.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				minTo--;
				textMin1.setText(minTo+"");
				if(minTo==0)
				{
					lTime10.setEnabled(false);
				}
			}
		});
		lTime7.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				hourTo--;
				textHour1.setText(hourTo+"");
				if(hourTo==0)
				{
					lTime7.setEnabled(false);
				}
			}
		});
		textActivity1=(TextView)findViewById(R.id.textActivity2);
		textActivity2=(TextView)findViewById(R.id.textActivity3);
		textActivity3=(TextView)findViewById(R.id.textActivity4);
		textActivity4=(TextView)findViewById(R.id.textAct1);
		textActivity5=(TextView)findViewById(R.id.textAct2);
		textActivity6=(TextView)findViewById(R.id.textAct3);
		gridActivity=(GridView)findViewById(R.id.gridActivity);
		gridActivity1=(GridView)findViewById(R.id.gridActivity1);
		ActivityAdapter aAdapter=new ActivityAdapter(EditActivity.this,imageName,imageActivity);
		gridActivity.setAdapter(aAdapter);
		gridActivity1.setAdapter(aAdapter);
		btnSubmit.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v)
			{
				isSelected=true;
				if(!editDescribe.getText().toString().equals(""))
				{
					if(isActivated1==true || isActivated2==true || isActivated3==true || isActivated4==true || isActivated5==true || 
							isActivated6==true || isActivated7==true)
					{
						if(isActivated8==true || !textZipCode.getText().toString().equals(""))
						{
							if(id1==1)
							{
								equalCondition1();
							}
							if(id1==2)
							{
								equalCondition2();
							}
							if(id1==3)
							{
								equalCondition3();
							}
							if(id1==4)
							{
								equalCondition4();
							}
							if(id1==5)
							{
								equalCondition5();
							}
							if(id1==6)
							{
								equalCondition6();
							}
							if(bitmap!=null&& isAct1==true)
							{
								getActivityData1();
								firstActivityData();
								isAct1=false;
							}	
							if(bitmap1!=null && isAct2==true)
							{
								getActivityData2();
								secondActivityData();
								isAct2=false;
							}
							if(bitmap2!=null  && isAct3==true)
							{
								getActivityData3();
								thirdActivityData();
								isAct3=false;
							}
							if(bitmap3!=null  && isAct4==true)
							{
								getActivityData4();
								fourthActivityData();
								isAct4=false;
							}
							if(bitmap4!=null  && isAct5==true)
							{
								getActivityData5();
								fifthActivityData();
								isAct5=false;
							}
							if(bitmap5!=null  && isAct6==true)
							{
								getActivityData6();
								sixthActivityData();
								isAct6=false;
							}	
							if(jsonObject!=null)
							{
								array.put(jsonObject);
							}
							Toast.makeText(getApplicationContext(), "You have submitted your activity", Toast.LENGTH_SHORT).show();	
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Please select the current location or zip code", Toast.LENGTH_SHORT).show();
							Animation shake = AnimationUtils.loadAnimation(EditActivity.this, R.anim.shake);
							findViewById(R.id.lEditActivity20).startAnimation(shake);
							Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
							v1.vibrate(300);
						}						
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Please select atleast one day", Toast.LENGTH_SHORT).show();
						Animation shake = AnimationUtils.loadAnimation(EditActivity.this, R.anim.shake);
						findViewById(R.id.lEditActivity5).startAnimation(shake);
						Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
						v1.vibrate(300);

					}
				}
				else
				{
					
					Toast.makeText(getApplicationContext(), "Please enter the description", Toast.LENGTH_SHORT).show();
					Animation shake = AnimationUtils.loadAnimation(EditActivity.this, R.anim.shake);
					findViewById(R.id.editDescribe).startAnimation(shake);
					Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
					v1.vibrate(300);

				}
			}
		});
		imgView1.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View arg0)
			{
				if(isSelected==true || firstActivity==1 || firstAgain==true)
				{					
					id1=1;	
					isAct1=true;
					backGroundColor();				
					for(int i=0;i<arrList1.size();i++)
					{
						secondAgain=false;
						thirdAgain=false;
						fourthAgain=false;
						fifthAgain=false;
						sixthAgain=false;
						editDescribe.setText(arrList1.get(0));
						textHour.setText(arrList1.get(1));
						textMin.setText(arrList1.get(2));
						textHour1.setText(arrList1.get(3));
						textMin1.setText(arrList1.get(4));
						
						for(int j=0;j<arrDay1.size();j++)
						{

							if(arrDay1.get(j).equals("MONDAY"))
							{
								lEditActivity6.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated1=true;							
							}

							if(arrDay1.get(j).equals("TUESDAY"))
							{
								lEditActivity7.setBackgroundColor(Color.parseColor("#2F978C"));	
								isActivated2=true;
							}

							if(arrDay1.get(j).equals("WEDNESDAY"))
							{
								lEditActivity8.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated3=true;
							}

							if(arrDay1.get(j).equals("THURSDAY"))
							{
								lEditActivity9.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated4=true;
							}

							if(arrDay1.get(j).equals("FRIDAY"))
							{
								lEditActivity10.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated5=true;
							}

							if(arrDay1.get(j).equals("SATURDAY"))
							{
								lEditActivity11.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated6=true;

							}

							if(arrDay1.get(j).equals("SUNDAY"))
							{
								lEditActivity12.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated7=true;
							}
						}}
					gridActivity.setVisibility(View.VISIBLE);
					imgArrow1.setVisibility(View.VISIBLE);
					imgArrow2.setVisibility(View.GONE);
					imgArrow3.setVisibility(View.GONE);
					imgArrow4.setVisibility(View.GONE);
					imgArrow5.setVisibility(View.GONE);
					imgArrow6.setVisibility(View.GONE);
					textActivity1.setVisibility(View.VISIBLE);
					gridActivity1.setVisibility(View.GONE);
					gridActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() 
					{
						@SuppressWarnings("deprecation")
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) 
						{						 									
							firstActivity++;
							firstAgain=true;
							String name=imageName[position];
							textActivity1.setText(name);
							activityType=name;
							imgView1.setImageDrawable(getResources().getDrawable(imageActivity[position]));	         
							gridActivity.setVisibility(View.GONE);
							imgArrow1.setVisibility(View.GONE);
							Drawable drawable=imgView1.getDrawable();
							bitmap=((BitmapDrawable)drawable).getBitmap();	
							isBitmap1=true;	
							isSelected=false;
						}
					});
				}
				else
				{
					dialogShow();
				}
			}		
		}		
	);
		imgView2.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View arg0)
			{
				if(isSelected==true || firstActivity==1 || secondAgain==true)
				{																		
					isAct2=true;
					firstAgain=false;
					thirdAgain=false;
					fourthAgain=false;
					fifthAgain=false;
					sixthAgain=false;
					id1=2;	
					backGroundColor();
					for(int i=0;i<arrList2.size();i++)
					{
						editDescribe.setText(arrList2.get(0));
						textHour.setText(arrList2.get(1));
						textMin.setText(arrList2.get(2));
						textHour1.setText(arrList2.get(3));
						textMin1.setText(arrList2.get(4));							
						for(int j=0;j<arrDay2.size();j++)
						{					
							if(arrDay2.get(j).equals("MONDAY"))
							{
								lEditActivity6.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated1=true;
							}				
							if(arrDay2.get(j).equals("TUESDAY"))
							{
								lEditActivity7.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated2=true;
							}					
							if(arrDay2.get(j).equals("WEDNESDAY"))
							{
								lEditActivity8.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated3=true;
							}					
							if(arrDay2.get(j).equals("THURSDAY"))
							{
								lEditActivity9.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated4=true;
							}					
							if(arrDay2.get(j).equals("FRIDAY"))
							{
								lEditActivity10.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated5=true;
							}					
							if(arrDay2.get(j).equals("SATURDAY"))
							{
								lEditActivity11.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated6=true;
							}				
							if(arrDay2.get(j).equals("SUNDAY"))
							{
								lEditActivity12.setBackgroundColor(Color.parseColor("#2F978C"));
								isActivated7=true;
							}					
						}	}
					gridActivity.setVisibility(View.VISIBLE);
					imgArrow2.setVisibility(View.VISIBLE);
					imgArrow1.setVisibility(View.GONE);
					imgArrow3.setVisibility(View.GONE);
					imgArrow4.setVisibility(View.GONE);
					imgArrow5.setVisibility(View.GONE);
					imgArrow6.setVisibility(View.GONE);
					textActivity2.setVisibility(View.VISIBLE);
					gridActivity1.setVisibility(View.GONE);
					gridActivity.setOnItemClickListener(new AdapterView.OnItemClickListener()
					{
						@SuppressWarnings("deprecation")
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) 
						{
							firstActivity++;
							secondAgain=true;
							String name=imageName[position];
							textActivity2.setText(name);
							activityType=name;
							imgView2.setImageDrawable(getResources().getDrawable(imageActivity[position]));	 
							gridActivity.setVisibility(View.GONE);
							imgArrow2.setVisibility(View.GONE);
							Drawable drawable1=imgView2.getDrawable();
							bitmap1=((BitmapDrawable)drawable1).getBitmap();
							isSelected=false;
						}
					});
				}
				else
				{
					dialogShow();
				}
			}
		});

		imgView3.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View arg0)
			{
				if(isSelected==true || firstActivity==1 || thirdAgain==true)
				{
					id1=3;
					isAct3=true;
					secondAgain=false;
					firstAgain=false;
					fourthAgain=false;
					fifthAgain=false;
					sixthAgain=false;
					backGroundColor();
					firstAgain=true;
					for(int i=0;i<arrList3.size();i++)
					{
						editDescribe.setText(arrList3.get(0));
						textHour.setText(arrList3.get(1));
						textMin.setText(arrList3.get(2));
						textHour1.setText(arrList3.get(3));
						textMin1.setText(arrList3.get(4));
					}	

					for(int j=0;j<arrDay3.size();j++)
					{
						if(arrDay3.get(j).equals("MONDAY"))
						{
							lEditActivity6.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay3.get(j).equals("TUESDAY"))
						{
							lEditActivity7.setBackgroundColor(Color.parseColor("#2F978C"));							
						}

						if(arrDay3.get(j).equals("WEDNESDAY"))
						{
							lEditActivity8.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay3.get(j).equals("THURSDAY"))
						{
							lEditActivity9.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay3.get(j).equals("FRIDAY"))
						{
							lEditActivity10.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay3.get(j).equals("SATURDAY"))
						{
							lEditActivity11.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay3.get(j).equals("SUNDAY"))
						{
							lEditActivity12.setBackgroundColor(Color.parseColor("#2F978C"));
						}							
					}	
					gridActivity.setVisibility(View.VISIBLE);
					imgArrow3.setVisibility(View.VISIBLE);
					imgArrow1.setVisibility(View.GONE);
					imgArrow2.setVisibility(View.GONE);
					imgArrow4.setVisibility(View.GONE);
					imgArrow5.setVisibility(View.GONE);
					imgArrow6.setVisibility(View.GONE);
					textActivity3.setVisibility(View.VISIBLE);
					gridActivity1.setVisibility(View.GONE);
					gridActivity.setOnItemClickListener(new AdapterView.OnItemClickListener()
					{
						@SuppressWarnings("deprecation")
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) 
						{	
							firstActivity++;
							thirdAgain=true;
							String name=imageName[position];
							textActivity3.setText(name);
							activityType=name;
							imgView3.setImageDrawable(getResources().getDrawable(imageActivity[position]));	
							gridActivity.setVisibility(View.GONE);
							imgArrow3.setVisibility(View.GONE);
							Drawable drawable2=imgView3.getDrawable();
							bitmap2=((BitmapDrawable)drawable2).getBitmap();
							isSelected=false;
						}
					});
				}
				else
				{
					dialogShow();
				}
			}
		});
		imgView4.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View arg0)
			{
				if(isSelected==true || firstActivity==1 || fourthAgain==true)
				{
					id1=4;	
					firstAgain=false;
					thirdAgain=false;
					secondAgain=false;
					fifthAgain=false;
					sixthAgain=false;
					backGroundColor();
					isAct4=true;
					for(int i=0;i<arrList4.size();i++)
					{
						editDescribe.setText(arrList4.get(0));
						textHour.setText(arrList4.get(1));
						textMin.setText(arrList4.get(2));
						textHour1.setText(arrList4.get(3));
						textMin1.setText(arrList4.get(4));
					}	
					for(int j=0;j<arrDay4.size();j++)
					{
						if(arrDay4.get(j).equals("MONDAY"))
						{
							lEditActivity6.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay4.get(j).equals("TUESDAY"))
						{
							lEditActivity7.setBackgroundColor(Color.parseColor("#2F978C"));							
						}

						if(arrDay4.get(j).equals("WEDNESDAY"))
						{
							lEditActivity8.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay4.get(j).equals("THURSDAY"))
						{
							lEditActivity9.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay4.get(j).equals("FRIDAY"))
						{
							lEditActivity10.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay4.get(j).equals("SATURDAY"))
						{
							lEditActivity11.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay4.get(j).equals("SUNDAY"))
						{
							lEditActivity12.setBackgroundColor(Color.parseColor("#2F978C"));
						}							
					}	
					gridActivity1.setVisibility(View.VISIBLE);
					imgArrow4.setVisibility(View.VISIBLE);
					imgArrow5.setVisibility(View.GONE);
					imgArrow6.setVisibility(View.GONE);
					imgArrow3.setVisibility(View.GONE);
					imgArrow2.setVisibility(View.GONE);
					imgArrow1.setVisibility(View.GONE);
					textActivity4.setVisibility(View.VISIBLE);
					gridActivity.setVisibility(View.GONE);
					gridActivity1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@SuppressWarnings("deprecation")
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) 
						{
							firstActivity++;
							fourthAgain=true;
							String name=imageName[position];
							textActivity4.setText(name);
							activityType=name;
							imgView4.setImageDrawable(getResources().getDrawable(imageActivity[position]));	
							gridActivity1.setVisibility(View.GONE);
							imgArrow4.setVisibility(View.GONE);
							Drawable drawable2=imgView4.getDrawable();
							bitmap3=((BitmapDrawable)drawable2).getBitmap();
							isSelected=false;
						}
					});			
				}
				else
				{
					dialogShow();
				}
			}
		});
		imgView5.setOnClickListener(new OnClickListener()
		{				
			@Override
			public void onClick(View arg0)
			{
				if(isSelected==true || firstActivity==1 || fifthAgain==true)
				{
					id1=5;
					firstAgain=false;
					thirdAgain=false;
					fourthAgain=false;
					secondAgain=false;
					sixthAgain=false;
					backGroundColor();
					isAct5=true;
					for(int i=0;i<arrList5.size();i++)
					{
						editDescribe.setText(arrList5.get(0));
						textHour.setText(arrList5.get(1));
						textMin.setText(arrList5.get(2));
						textHour1.setText(arrList5.get(3));
						textMin1.setText(arrList5.get(4));
					}
					for(int j=0;j<arrDay5.size();j++)
					{
						if(arrDay5.get(j).equals("MONDAY"))
						{
							lEditActivity6.setBackgroundColor(Color.parseColor("#2F978C"));
						}

						if(arrDay5.get(j).equals("TUESDAY"))
						{
							lEditActivity7.setBackgroundColor(Color.parseColor("#2F978C"));

						}

						if(arrDay5.get(j).equals("WEDNESDAY"))
						{
							lEditActivity8.setBackgroundColor(Color.parseColor("#2F978C"));	
						}							
						if(arrDay5.get(j).equals("THURSDAY"))
						{
							lEditActivity9.setBackgroundColor(Color.parseColor("#2F978C"));		
						}							
						if(arrDay5.get(j).equals("FRIDAY"))
						{
							lEditActivity10.setBackgroundColor(Color.parseColor("#2F978C"));	
						}							
						if(arrDay5.get(j).equals("SATURDAY"))
						{
							lEditActivity11.setBackgroundColor(Color.parseColor("#2F978C"));		
						}

						if(arrDay5.get(j).equals("SUNDAY"))
						{
							lEditActivity12.setBackgroundColor(Color.parseColor("#2F978C"));	
						}

					}		
					gridActivity1.setVisibility(View.VISIBLE);
					imgArrow5.setVisibility(View.VISIBLE);
					imgArrow4.setVisibility(View.GONE);
					imgArrow6.setVisibility(View.GONE);
					imgArrow3.setVisibility(View.GONE);
					imgArrow2.setVisibility(View.GONE);
					imgArrow1.setVisibility(View.GONE);
					textActivity5.setVisibility(View.VISIBLE);
					gridActivity.setVisibility(View.GONE);
					gridActivity1.setOnItemClickListener(new AdapterView.OnItemClickListener()
					{
						@SuppressWarnings("deprecation")
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) 
						{
							firstActivity++;
							fifthAgain=true;
							String name=imageName[position];
							textActivity5.setText(name);
							activityType=name;
							imgView5.setImageDrawable(getResources().getDrawable(imageActivity[position]));	
							gridActivity1.setVisibility(View.GONE);
							imgArrow5.setVisibility(View.GONE);
							Drawable drawable2=imgView5.getDrawable();
							bitmap4=((BitmapDrawable)drawable2).getBitmap();
							isSelected=false;
						}
					});			
				}
				else
				{
					dialogShow();
				}
			}
		});
		imgView6.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View arg0)
			{
				if(isSelected==true || firstActivity==1 || sixthAgain==true)
				{
					id1=6;
					isAct6=true;
					backGroundColor();
					firstAgain=false;
					thirdAgain=false;
					fourthAgain=false;
					fifthAgain=false;
					secondAgain=false;
					for(int i=0;i<arrList6.size();i++)
					{
						editDescribe.setText(arrList6.get(0));
						textHour.setText(arrList6.get(1));
						textMin.setText(arrList6.get(2));
						textHour1.setText(arrList6.get(3));
						textMin1.setText(arrList6.get(4));			
					}
					for(int j=0;j<arrDay6.size();j++)
					{
						if(arrDay6.get(j).equals("MONDAY"))
						{
							lEditActivity6.setBackgroundColor(Color.parseColor("#2F978C"));
						}					
						if(arrDay6.get(j).equals("TUESDAY"))
						{
							lEditActivity7.setBackgroundColor(Color.parseColor("#2F978C"));
						}					
						if(arrDay6.get(j).equals("WEDNESDAY"))
						{
							lEditActivity8.setBackgroundColor(Color.parseColor("#2F978C"));
						}					
						if(arrDay6.get(j).equals("THURSDAY"))
						{
							lEditActivity9.setBackgroundColor(Color.parseColor("#2F978C"));
						}				
						if(arrDay6.get(j).equals("FRIDAY"))
						{
							lEditActivity10.setBackgroundColor(Color.parseColor("#2F978C"));
						}			
						if(arrDay6.get(j).equals("SATURDAY"))
						{
							lEditActivity11.setBackgroundColor(Color.parseColor("#2F978C"));
						}
						if(arrDay6.get(j).equals("SUNDAY"))
						{
							lEditActivity12.setBackgroundColor(Color.parseColor("#2F978C"));
						}							
					}			
					gridActivity1.setVisibility(View.VISIBLE);
					imgArrow6.setVisibility(View.VISIBLE);
					imgArrow5.setVisibility(View.GONE);
					imgArrow4.setVisibility(View.GONE);
					imgArrow3.setVisibility(View.GONE);
					imgArrow2.setVisibility(View.GONE);
					imgArrow1.setVisibility(View.GONE);
					textActivity6.setVisibility(View.VISIBLE);
					gridActivity.setVisibility(View.GONE);
					gridActivity1.setOnItemClickListener(new AdapterView.OnItemClickListener()
					{
						@SuppressWarnings("deprecation")
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) 
						{	
							firstActivity++;
							sixthAgain=true;
							String name=imageName[position];
							textActivity6.setText(name);
							activityType=name;
							imgView6.setImageDrawable(getResources().getDrawable(imageActivity[position]));	   
							imgArrow6.setVisibility(View.GONE);
							gridActivity1.setVisibility(View.GONE);
							Drawable drawable2=imgView6.getDrawable();
							bitmap5=((BitmapDrawable)drawable2).getBitmap();
							isSelected=false;
						}
					});			
				}
				else
				{
					dialogShow();
				}
			}
		});	
		lEditActivity2.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View arg0) 
			{
				gridActivity.setVisibility(View.GONE);
				imgArrow1.setVisibility(View.GONE);
				imgArrow2.setVisibility(View.GONE);
				imgArrow3.setVisibility(View.GONE);
				gridActivity1.setVisibility(View.GONE);
				imgArrow4.setVisibility(View.GONE);
				imgArrow5.setVisibility(View.GONE);
				imgArrow6.setVisibility(View.GONE);
			}
		});
		lEditActivity4.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View arg0) 
			{
				gridActivity1.setVisibility(View.GONE);
				imgArrow4.setVisibility(View.GONE);
				imgArrow5.setVisibility(View.GONE);
				imgArrow6.setVisibility(View.GONE);
			}
		});
		lEditActivity=(RelativeLayout)findViewById(R.id.lEditActivity);
		lEditActivity.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(EditActivity.this,CreateAccount.class);
				startActivity(intent);
			}
		});
	}	
	@SuppressWarnings("deprecation")
	public  String POST(String url)
	{
		arrGetList=new ArrayList<GetActivityList>();
		InputStream inputStream = null;		
		String result = "";
		try
		{
			@SuppressWarnings("deprecation")
			HttpClient httpclient = new DefaultHttpClient();
			@SuppressWarnings("deprecation")
			HttpPost httpPost = new HttpPost(url);
			String json = "";			
			json = array.toString();
			@SuppressWarnings("deprecation")
			StringEntity se = new StringEntity(json);
			httpPost.setEntity(se);
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setHeader("userid",strId);
			arrGetType=new ArrayList<String>();
			HttpResponse httpResponse = httpclient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();
			if (inputStream != null)
			{
				result = convertInputStreamToString(inputStream);
				JSONObject jobj = new JSONObject(result);
				valid=jobj.getString("valid");
				JSONArray jarr=jobj.getJSONArray("listentity");
				for(int i=0;i<jarr.length();i++)
				{
					JSONObject jobj1=jarr.getJSONObject(i);
					getDescription=jobj1.getString("description");
					gettype=jobj1.getString("type");
					getstartTime=jobj1.getString("startTime");
					getendTime=jobj1.getString("endTime");
					JSONArray jarr1=jobj1.getJSONArray("daysInString");
					arrGetDays=new ArrayList<String>();
					for(int j=0;j<jarr1.length();j++)
					{
						getdays=jarr1.getString(j);
						arrGetDays.add(getdays);
					}
					Bitmap bitmapFinal = null;
					GetActivityList gList=new GetActivityList();
					gList.setGetDescription(getDescription);
					gList.setGetType(gettype);
					gList.setGetStartTime(getstartTime);
					gList.setGetEndTime(getendTime);
					gList.setArrGetDays(arrGetDays);	
					arrGetType.add(gettype);
					for(int j=0;j<imageName.length;j++)
					{
						if(imageName[j].equalsIgnoreCase(gettype))
						{
							int drawable=imageActivity[j];
							bitmapFinal=BitmapFactory.decodeResource(getResources(),drawable);
						}
					}
					gList.setImgActivity(bitmapFinal);							
					arrGetList.add(gList);	
				
				}
			}
			else 
			{

			}
		} 
		catch(Exception e) 
		{
			Log.d("InputStream", e.getLocalizedMessage());
		}
		return result;
	}
	@SuppressWarnings("unused")
	private class HttpAsyncTask extends AsyncTask<String, Void, String>
	{
		ProgressDialog pDialog;
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(EditActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... urls)
		{
			return POST(urls[0]);
		}
		@Override
		protected void onPostExecute(String result)
		{

			if(valid.equalsIgnoreCase("true"))
			{
				pDialog.cancel();						
				Intent intent = new Intent(EditActivity.this,EditActivityList.class);						
				startActivity(intent);
			}}
	}
	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException
			{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;
		inputStream.close();
		return result;
			}
	public static Bitmap decodeBase64(String input)
	{
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}
	public static void getActivityData1()
	{	
		getData();	
		setActivityData1();
		hmap.put("first",arrActivityList1);

	}
	public static void getActivityData2()
	{	
		getData();	
		setActivityData2();
		hmap.put("second",arrActivityList2);
	}
	public static void getActivityData3()
	{	
		getData();	
		setActivityData3();
		hmap.put("third",arrActivityList3);
	}
	public static void getActivityData4()
	{	
		getData();	
		setActivityData4();
		hmap.put("fourth",arrActivityList4);
	}
	public static void getActivityData5()
	{	
		getData();	
		setActivityData5();
		hmap.put("fifth",arrActivityList5);
	}
	public static void getActivityData6()
	{	
		getData();	
		setActivityData6();
		hmap.put("sixth",arrActivityList6);
	}	
	public static void setActivityData1()
	{
		setData();
		arrActivityList1.add(aList);	
	}	
	public static void setActivityData2()
	{
		setData();
		arrActivityList2.add(aList);	
	}	
	public static void setActivityData3()
	{
		setData();
		arrActivityList3.add(aList);	
	}	
	public static void setActivityData4()
	{
		setData();
		arrActivityList4.add(aList);	
	}	
	public static void setActivityData5()
	{
		setData();
		arrActivityList5.add(aList);	
	}	
	public static void setActivityData6()
	{
		setData();
		arrActivityList6.add(aList);	
	}	

	public static void setData()
	{
		aList=new ActivityList();	
		checkActivity(activityType);
	
		aList.setDescription(description);
		aList.setStartTime(startTime);
		aList.setEndTime(endTime);
		aList.setArrDays(arrDays);
		aList.setPostalCode(strZipCode);
		aList.setLatitude(latitude);
		aList.setLongitude(longitude);
	}
	public static void getJSONObject1() throws JSONException 
	{

		jsonObject = new JSONObject();
		jsonObject.put("description",hmap.get("first").get(0).getDescription());		
		JSONArray jarr=new JSONArray(hmap.get("first").get(0).getArrDays());			
		jsonObject.put("days",jarr);
		jsonObject.put("activityType",hmap.get("first").get(0).getActivityType().toUpperCase());		
		jsonObject.put("startTime",hmap.get("first").get(0).getStartTime());
		jsonObject.put("endTime",hmap.get("first").get(0).getEndTime());		
		if(isActivated8==true)
		{
			jsonObject.put("latitude",hmap.get("first").get(0).getLatitude());
			jsonObject.put("longitude",hmap.get("first").get(0).getLongitude());
		}
		else
		{
			jsonObject.put("postalCode",hmap.get("first").get(0).getPostalCode());
		}
	}
	public static void getJSONObject2() throws JSONException 
	{

		jsonObject = new JSONObject();
		jsonObject.put("description",hmap.get("second").get(0).getDescription());		
		JSONArray jarr=new JSONArray(hmap.get("second").get(0).getArrDays());			
		jsonObject.put("days",jarr);
		jsonObject.put("activityType",hmap.get("second").get(0).getActivityType().toUpperCase());		
		jsonObject.put("startTime",hmap.get("second").get(0).getStartTime());
		jsonObject.put("endTime",hmap.get("second").get(0).getEndTime());		
		if(isActivated8==true)
		{
			jsonObject.put("latitude",hmap.get("second").get(0).getLatitude());
			jsonObject.put("longitude",hmap.get("second").get(0).getLongitude());
		}
		else
		{
			jsonObject.put("postalCode",hmap.get("second").get(0).getPostalCode());
		}	

	}
	public static void getJSONObject3() throws JSONException 
	{

		jsonObject = new JSONObject();
		jsonObject.put("description",hmap.get("third").get(0).getDescription());		
		JSONArray jarr=new JSONArray(hmap.get("third").get(0).getArrDays());			
		jsonObject.put("days",jarr);
		jsonObject.put("activityType",hmap.get("third").get(0).getActivityType().toUpperCase());		
		jsonObject.put("startTime",hmap.get("third").get(0).getStartTime());
		jsonObject.put("endTime",hmap.get("third").get(0).getEndTime());		
		if(isActivated8==true)
		{
			jsonObject.put("latitude",hmap.get("third").get(0).getLatitude());
			jsonObject.put("longitude",hmap.get("third").get(0).getLongitude());
		}
		else
		{
			jsonObject.put("postalCode",hmap.get("third").get(0).getPostalCode());
		}		
	}
	public static void getJSONObject4() throws JSONException 
	{

		jsonObject = new JSONObject();
		jsonObject.put("description",hmap.get("fourth").get(0).getDescription());		
		JSONArray jarr=new JSONArray(hmap.get("fourth").get(0).getArrDays());			
		jsonObject.put("days",jarr);
		jsonObject.put("activityType",hmap.get("fourth").get(0).getActivityType().toUpperCase());		
		jsonObject.put("startTime",hmap.get("fourth").get(0).getStartTime());
		jsonObject.put("endTime",hmap.get("fourth").get(0).getEndTime());		
		if(isActivated8==true)
		{
			jsonObject.put("latitude",hmap.get("fourth").get(0).getLatitude());
			jsonObject.put("longitude",hmap.get("fourth").get(0).getLongitude());
		}
		else
		{
			jsonObject.put("postalCode",hmap.get("fourth").get(0).getPostalCode());
		}		
	}
	public static void getJSONObject5() throws JSONException 
	{

		jsonObject = new JSONObject();
		jsonObject.put("description",hmap.get("fifth").get(0).getDescription());		
		JSONArray jarr=new JSONArray(hmap.get("fifth").get(0).getArrDays());			
		jsonObject.put("days",jarr);
		jsonObject.put("activityType",hmap.get("fifth").get(0).getActivityType().toUpperCase());		
		jsonObject.put("startTime",hmap.get("fifth").get(0).getStartTime());
		jsonObject.put("endTime",hmap.get("fifth").get(0).getEndTime());		
		if(isActivated8==true)
		{
			jsonObject.put("latitude",hmap.get("fifth").get(0).getLatitude());
			jsonObject.put("longitude",hmap.get("fifth").get(0).getLongitude());
		}
		else
		{
			jsonObject.put("postalCode",hmap.get("fifth").get(0).getPostalCode());
		}

	}
	public static void getJSONObject6() throws JSONException 
	{				
		jsonObject = new JSONObject();
		jsonObject.put("description",hmap.get("sixth").get(0).getDescription());		
		JSONArray jarr=new JSONArray(hmap.get("sixth").get(0).getArrDays());			
		jsonObject.put("days",jarr);
		jsonObject.put("activityType",hmap.get("sixth").get(0).getActivityType().toUpperCase());		
		jsonObject.put("startTime",hmap.get("sixth").get(0).getStartTime());
		jsonObject.put("endTime",hmap.get("sixth").get(0).getEndTime());		
		if(isActivated8==true)
		{
			jsonObject.put("latitude",hmap.get("sixth").get(0).getLatitude());
			jsonObject.put("longitude",hmap.get("sixth").get(0).getLongitude());
		}
		else
		{
			jsonObject.put("postalCode",hmap.get("sixth").get(0).getPostalCode());
		}			
	}
	public static void firstActivityData()
	{
		try
		{
			getJSONObject1();
		} 
		catch (JSONException e)
		{
			e.printStackTrace();
		}		
	}
	public static void secondActivityData()
	{
		try
		{
			getJSONObject2();
		} 
		catch (JSONException e)
		{
			e.printStackTrace();
		}		
	}
	public static void thirdActivityData()
	{
		try
		{
			getJSONObject3();
		} 
		catch (JSONException e)
		{
			e.printStackTrace();
		}		
	}
	public static void fourthActivityData()
	{
		try
		{
			getJSONObject4();
		} 
		catch (JSONException e)
		{
			e.printStackTrace();
		}		
	}
	public static void fifthActivityData()
	{
		try
		{
			getJSONObject5();
		} 
		catch (JSONException e)
		{
			e.printStackTrace();
		}		
	}
	public static void sixthActivityData()
	{
		try
		{
			getJSONObject6();
		} 
		catch (JSONException e)
		{
			e.printStackTrace();
		}		
	}
	public static void getData()
	{
		strZipCode=textZipCode.getText().toString();
		description=editDescribe.getText().toString();				
		arrGetDays=new ArrayList<String>();	
		startTime=textHour.getText().toString()+":"+textMin.getText().toString();
		endTime=textHour1.getText().toString()+":"+textMin1.getText().toString();
		arrDays.clear();
		if(isActivated1==true)
		{
			arrDays.add("MONDAY");
		}
		if(isActivated2==true)
		{
			arrDays.add("TUESDAY");
		}
		if(isActivated3==true)
		{
			arrDays.add("WEDNESDAY");
		}
		if(isActivated4==true)
		{
			arrDays.add("THURSDAY");
		}
		if(isActivated5==true)
		{
			arrDays.add("FRIDAY");
		}
		if(isActivated6==true)
		{
			arrDays.add("SATURDAY");
		}
		if(isActivated7==true)
		{
			arrDays.add("SUNDAY");
		}
	}	
	public static void backGroundColor()
	{
		lEditActivity6.setBackgroundColor(Color.parseColor("#40B8AC"));
		lEditActivity7.setBackgroundColor(Color.parseColor("#40B8AC"));
		lEditActivity8.setBackgroundColor(Color.parseColor("#40B8AC"));
		lEditActivity9.setBackgroundColor(Color.parseColor("#40B8AC"));
		lEditActivity10.setBackgroundColor(Color.parseColor("#40B8AC"));
		lEditActivity11.setBackgroundColor(Color.parseColor("#40B8AC"));
		lEditActivity12.setBackgroundColor(Color.parseColor("#40B8AC"));
		isActivated1=false;isActivated2=false;isActivated3=false;isActivated4=false;isActivated5=false;isActivated6=false;isActivated7=false;
	}
	public static void equalCondition1()
	{																		
		arrList1.clear();
		getData();	
		arrDay1.addAll(arrDays);
		arrList1.add(0,description);
		arrList1.add(1,textHour.getText().toString());
		arrList1.add(2,textMin.getText().toString());
		arrList1.add(3,textHour1.getText().toString());
		arrList1.add(4,textMin1.getText().toString());

	}	
	public static void equalCondition2()
	{		
		arrList2.clear();					
		arrDay2.clear();
		getData();						
		arrDay2.addAll(arrDays);
		arrList2.add(0,description);
		arrList2.add(1,textHour.getText().toString());
		arrList2.add(2,textMin.getText().toString());
		arrList2.add(3,textHour1.getText().toString());
		arrList2.add(4,textMin1.getText().toString());																				
	}	
	public static void equalCondition3()
	{				
		arrList3.clear();
		arrDay3.clear();
		getData();
		arrDay3.addAll(arrDays);
		arrList3.add(0,description);
		arrList3.add(1,textHour.getText().toString());
		arrList3.add(2,textMin.getText().toString());
		arrList3.add(3,textHour1.getText().toString());
		arrList3.add(4,textMin1.getText().toString());					
	}
	public static void equalCondition4()
	{		
		arrList4.clear();					
		arrDay4.clear();
		getData();
		arrDay4.addAll(arrDays);
		arrList4.add(0,description);
		arrList4.add(1,textHour.getText().toString());
		arrList4.add(2,textMin.getText().toString());
		arrList4.add(3,textHour1.getText().toString());
		arrList4.add(4,textMin1.getText().toString());																			
	}	
	public static void equalCondition5()
	{		
		arrList5.clear();					
		arrDay5.clear();
		getData();
		arrDay5.addAll(arrDays);
		arrList5.add(0,description);
		arrList5.add(1,textHour.getText().toString());
		arrList5.add(2,textMin.getText().toString());
		arrList5.add(3,textHour1.getText().toString());
		arrList5.add(4,textMin1.getText().toString());

	}
	public static void equalCondition6()
	{		
		arrDay6.clear();
		getData();
		arrDay6.addAll(arrDays);
		arrList6.add(0,description);
		arrList6.add(1,textHour.getText().toString());
		arrList6.add(2,textMin.getText().toString());
		arrList6.add(3,textHour1.getText().toString());
		arrList6.add(4,textMin1.getText().toString());																				
	}	
	@Override
	public void onBackPressed() 
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finish();
	}
	public void dialogShow()
	{
		final Dialog dialog=new Dialog(EditActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);			
		dialog.setContentView(R.layout.saveactivity);
		Button btnOk=(Button)dialog.findViewById(R.id.btnOk1);

		btnOk.setOnClickListener(new OnClickListener() 
		{					
			@Override
			public void onClick(View arg0) 
			{
				dialog.cancel();
			}
		});

		dialog.show();
	}
	public void storeFavorites(Context context, ArrayList<GetActivityList> favorites)
	{
		SharedPreferences settings;
		Editor editor;
		settings = context.getSharedPreferences("MyPref",Context.MODE_PRIVATE);
		editor = settings.edit();
		Gson gson = new Gson();
		String jsonFavorites = gson.toJson(favorites);
		editor.putString("activity", jsonFavorites);
		editor.commit();
	}

public static void checkActivity(String activity)
{
     switch(activity)
     {
      case "Indoor Sports": 
    	  activity="INDOOR_SPORTS";
    		aList.setActivityType(activity);
           break; 
      case "Cycling etc": 
    	  activity="CYCLING_ETC";
    		aList.setActivityType(activity);
           break; 
      case "Adventure Sports": 
    	  activity="CYCLING_ETC";
    		aList.setActivityType(activity);
           break; 
      case "Outdoor Sports": 
    	  activity="OUTDOOR_SPORTS";
    		aList.setActivityType(activity);
    	  break; 
      case "Events": 
    	  activity="EVENTS";
    		aList.setActivityType(activity);
    	  break; 
      case "Water Activities": 
    	  activity="WATER_ACTIVITIES";
    		aList.setActivityType(activity);
    	  break; 
      case "Dog Walking": 
    	  activity="DOG_WALKING";
    		aList.setActivityType(activity);
    	  break; 
      case "Playing Music": 
    	  activity="PLAYING_MUSIC";
    		aList.setActivityType(activity);
    	  break;
      case "Video Games": 
    	  activity="VIDEO_GAMES";
    		aList.setActivityType(activity);
    	  break; 
      case "Bars,Dining": 
    	  activity="BARS_DINING";
    		aList.setActivityType(activity);
    	  break;     
      	default: 
    	 activity=activity;
 		aList.setActivityType(activity);
           break;           
     } 
} 
}



