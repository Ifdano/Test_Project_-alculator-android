/*
 Тестовая демо-версия калькулятора
 */

package com.example.elsetestapp2;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;

import android.widget.ImageButton;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main extends Activity implements OnTouchListener{
	
	//поле для вывода и кнопки
	private TextView display;
	
	private ImageButton number_0;
	private ImageButton number_1;
	private ImageButton number_2;
	private ImageButton number_3;
	private ImageButton number_4;
	private ImageButton number_5;
	private ImageButton number_6;
	private ImageButton number_7;
	private ImageButton number_8;
	private ImageButton number_9;
	
	private ImageButton butDecimal;
	private ImageButton butDelete;
	private ImageButton butClear;
	private ImageButton butSqr;
	
	private ImageButton butAdd;
	private ImageButton butSub;
	private ImageButton butDiv;
	private ImageButton butMul;
	private ImageButton butResult;
	
	//операции
	private boolean operAdd;
	private boolean operSub;
	private boolean operMul;
	private boolean operDiv;
	
	private boolean operDec;
	private boolean operResult;
	private boolean operResultDelete;
	private boolean operResultDeleteFuck;
	
	//проверки на старт приложения, на деление на 0 и так далее
	private boolean isStart;
	private boolean isStartCopy;
	private boolean isStartCopyFuck;
	private boolean isDelete;
	private boolean isResult;
	private boolean isDivByZero;
	
	//исходные числа для работы
	private double number;
	private double result;
	private double tempResult;
	
	//строковые переменные для хранения чисел 
	private String tempNumber;
	private String tempDelete;
	private String tempResultOutput;
	private String tempResultOutputEnd;
	
	private int posDecimal;
	
	//для точных операций
	private BigDecimal tempResultCopy;
	private BigDecimal numberCopy;
	private BigDecimal resultCopy;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//инициализация переменных
		init();
		
		//установка слушателей
		addListeners();
	}
	
	public void init(){
		//инициализация кнопок
		number_0 = (ImageButton)findViewById(R.id.button0);
		number_1 = (ImageButton)findViewById(R.id.button1);
		number_2 = (ImageButton)findViewById(R.id.button2);
		number_3 = (ImageButton)findViewById(R.id.button3);
		number_4 = (ImageButton)findViewById(R.id.button4);
		number_5 = (ImageButton)findViewById(R.id.button5);
		number_6 = (ImageButton)findViewById(R.id.button6);
		number_7 = (ImageButton)findViewById(R.id.button7);
		number_8 = (ImageButton)findViewById(R.id.button8);
		number_9 = (ImageButton)findViewById(R.id.button9);
		
		butClear = (ImageButton)findViewById(R.id.buttonC);
		butDelete = (ImageButton)findViewById(R.id.buttonDelete);
		butDecimal = (ImageButton)findViewById(R.id.buttonDec);
		butSqr = (ImageButton)findViewById(R.id.buttonSqr);
		
		butAdd = (ImageButton)findViewById(R.id.buttonAdd);
		butDiv = (ImageButton)findViewById(R.id.buttonDiv);
		butSub = (ImageButton)findViewById(R.id.buttonSub);
		butMul = (ImageButton)findViewById(R.id.buttonMul);
		butResult = (ImageButton)findViewById(R.id.buttonEqual);
		
		display = (TextView)findViewById(R.id.textOutput);
		
		//начальные значения
		tempNumber = "";
		posDecimal = -1;
		
		operAdd = false;
		operSub = false;
		operMul = false;
		operDiv = false;
		operResult = false;
		operResultDelete = false;
		operResultDeleteFuck = false;
		operDec = false;
		
		isStart = false;
		isStartCopy = false;
		isStartCopyFuck = false;
		isDelete = false;
		isResult = true;
		isDivByZero = false;
	}
	
	//установка слушателей кнопок
	public void addListeners(){
		number_0.setOnTouchListener(this);
		number_1.setOnTouchListener(this);
		number_2.setOnTouchListener(this);
		number_3.setOnTouchListener(this);
		number_4.setOnTouchListener(this);
		number_5.setOnTouchListener(this);
		number_6.setOnTouchListener(this);
		number_7.setOnTouchListener(this);
		number_8.setOnTouchListener(this);
		number_9.setOnTouchListener(this);
		
		butAdd.setOnTouchListener(this);
		butSub.setOnTouchListener(this);
		butMul.setOnTouchListener(this);
		butDiv.setOnTouchListener(this);
		butResult.setOnTouchListener(this);
		
		butDecimal.setOnTouchListener(this);
		butDelete.setOnTouchListener(this);
		butSqr.setOnTouchListener(this);
		butClear.setOnTouchListener(this);
	}
	
	public boolean onTouch(View view, MotionEvent event){
		
		//если коснулись экрана
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			
			//если коснулись цифровые кнопки
			if(view.getId() == R.id.button0 || view.getId() == R.id.button1 ||
			   view.getId() == R.id.button2 || view.getId() == R.id.button3 ||
			   view.getId() == R.id.button4 || view.getId() == R.id.button5 ||
			   view.getId() == R.id.button6 || view.getId() == R.id.button7 ||
			   view.getId() == R.id.button8 || view.getId() == R.id.button9){
				
				//если коснулись 0
				if(view.getId() == R.id.button0){
					
					/*
					если поле пустое, то добавляем 0
					если не пустое, то
					если число начинается на "0.", то очистить и просто присвоить 0
					иначе, просто добавить 0
					 */
					if(!tempNumber.isEmpty()){
						if(tempNumber.length() == 1 && tempNumber.charAt(0) == '0'){
							tempNumber = "";
							tempNumber += "0";
						}else
							tempNumber += "0";
					}else{
						tempNumber += "0";
						result = 0;
					}
				}
				
				//если коснулись 1, 2 и так далее
				if(view.getId() == R.id.button1)
					if(tempNumber.length() == 1 && tempNumber.charAt(0) == '0'){
						tempNumber = "";
						tempNumber += "1";
					}else
						tempNumber += "1";
				
				if(view.getId() == R.id.button2)
					if(tempNumber.length() == 1 && tempNumber.charAt(0) == '0'){
						tempNumber = "";
						tempNumber += "2";
					}else
						tempNumber += "2";
				
				if(view.getId() == R.id.button3)
					if(tempNumber.length() == 1 && tempNumber.charAt(0) == '0'){
						tempNumber = "";
						tempNumber += "3";
					}else
						tempNumber += "3";
				
				if(view.getId() == R.id.button4)
					if(tempNumber.length() == 1 && tempNumber.charAt(0) == '0'){
						tempNumber = "";
						tempNumber += "4";
					}else
						tempNumber += "4";
						
				if(view.getId() == R.id.button5)
					if(tempNumber.length() == 1 && tempNumber.charAt(0) == '0'){
						tempNumber = "";
						tempNumber += "5";
					}else
						tempNumber += "5";
						
				if(view.getId() == R.id.button6)
					if(tempNumber.length() == 1 && tempNumber.charAt(0) == '0'){
						tempNumber = "";
						tempNumber += "6";
					}else
						tempNumber += "6";
						
				if(view.getId() == R.id.button7)
					if(tempNumber.length() == 1 && tempNumber.charAt(0) == '0'){
						tempNumber = "";
						tempNumber += "7";
					}else
						tempNumber += "7";
				
				if(view.getId() == R.id.button8)
					if(tempNumber.length() == 1 && tempNumber.charAt(0) == '0'){
						tempNumber = "";
						tempNumber += "8";
					}else
						tempNumber += "8";
				
				if(view.getId() == R.id.button9)
					if(tempNumber.length() == 1 && tempNumber.charAt(0) == '0'){
						tempNumber = "";
						tempNumber += "9";
					}else
						tempNumber += "9";
						
				//выводим введенные цифры на экран
				display.setText(tempNumber);
			}
			
			//если коснулись базовых операций
			if(view.getId() == R.id.buttonAdd || view.getId() == R.id.buttonSub ||
					   view.getId() == R.id.buttonMul || view.getId() == R.id.buttonDiv ||
					   view.getId() == R.id.buttonEqual){
						
						operDec = false;
					
						//проверка, если число заканчивается на ".", то удалить ее
						if(isDelete){
							if(!tempNumber.isEmpty())
								if(tempNumber.charAt(tempNumber.length()-1) == '.'){
									tempDelete = tempNumber.substring(0, tempNumber.length()-1);
									tempNumber = tempDelete;
								
									posDecimal = -1;
								}
							
							if(!tempNumber.isEmpty())
								result = Double.parseDouble(tempNumber);
							isDelete = false;
						}
						
						//присваиваем предыдущий результат, если он есть
						if(isResult && !tempNumber.isEmpty()){
							result = Double.parseDouble(tempNumber);
							isResult = false;
						}
						
						if(!tempNumber.isEmpty() || operResult){
							
							//повторная проверка на окончание "."
							if(!tempNumber.isEmpty())
								if(tempNumber.charAt(tempNumber.length()-1) == '.'){
									tempDelete = tempNumber.substring(0, tempNumber.length()-1);
									tempNumber = tempDelete;
									
									posDecimal = -1;
								}
							
							//переводим введенное число в double
							if(!tempNumber.isEmpty())
								number = Double.parseDouble(tempNumber);
							
							//если это первое вхождение в программу, то первое число и есть результат
							if(!isStart || isStartCopyFuck){
								result = number;
								isStart = true;
								isStartCopy = true;
								isStartCopyFuck = false;
							}
							
							if(operResult)
								operResult = false;
							
							//сложение, вычитание, умножение и деление - стандартные
							if(operAdd){
								tempResultCopy = new BigDecimal("" + tempResult);
								numberCopy = new BigDecimal("" + number);
								resultCopy = tempResultCopy.add(numberCopy);
								
								result = resultCopy.doubleValue();
								operAdd = false;
							}
							
							if(operSub){
								tempResultCopy = new BigDecimal("" + tempResult);
								numberCopy = new BigDecimal("" + number);
								resultCopy = tempResultCopy.subtract(numberCopy);
								
								result = resultCopy.doubleValue();
								operSub = false;
							}
							
							if(operMul){
								tempResultCopy = new BigDecimal(""+tempResult);
								numberCopy = new BigDecimal(""+number);
								resultCopy = tempResultCopy.multiply(numberCopy);
								
								result = resultCopy.doubleValue();
								operMul = false;
							}
							
							if(operDiv){
								if(number == 0){
									result = 0;
									tempResult = 0;
									tempNumber = "";
								
									isDivByZero = true;
								}else{
									tempResultCopy = new BigDecimal("" + tempResult);
									numberCopy = new BigDecimal("" + number);
									resultCopy = tempResultCopy.divide(numberCopy, 16, RoundingMode.HALF_UP);
									
									result = resultCopy.doubleValue();
								}
								
								operDiv = false;
							}
							
							//если используем базовые операции
							if(!operAdd && !operSub && !operMul && !operDiv){
								
								//сложение, вычитание, умножение и деление
								if(view.getId() == R.id.buttonAdd){
									operAdd = true;
									tempResult = result;
									display.setText("+");
								}
								
								if(view.getId() == R.id.buttonSub){
									operSub = true;
									tempResult = result;
									display.setText("-");
								}
								
								if(view.getId() == R.id.buttonMul){
									operMul = true;
									tempResult = result;
									display.setText("*");
								}
								
								if(view.getId() == R.id.buttonDiv){
									operDiv = true;
									tempResult = result;
									display.setText("/");
								}
								
								//если нажали "="
								if(view.getId() == R.id.buttonEqual){
									operResult = true;
									tempResult = result;
									
									/*
									 если мы делим на 0, то выводим ошибку
									 иначе, выводим результат 
									 */
									if(isDivByZero){
										display.setText("Cannot divide by zero");
										isDivByZero = false;
									}else{
										tempResultOutput = Double.toString(result);
										
										if(tempResultOutput.length() > 12){
											tempResultOutputEnd = tempResultOutput.substring(0, tempResultOutput.length()-1);
											result = Double.parseDouble(tempResultOutputEnd);
										}else
											if(tempResultOutput.charAt(tempResultOutput.length()-1) == '0' &&
											   tempResultOutput.charAt(tempResultOutput.length()-2) == '.')
													operResultDelete = true;
											
										if(operResultDelete){
											display.setText("" + (int)result);
											operResultDelete = false;
										}else	
											display.setText("" + result);
									}
									
								}
								
								tempNumber = "";
								
							}
						}
					}
			
			//если коснулись побочных операций
			if(view.getId() == R.id.buttonC || view.getId() == R.id.buttonDelete ||
			   view.getId() == R.id.buttonDec || view.getId() == R.id.buttonSqr){
				
				//если добавляем десятичную точку
				if(view.getId() == R.id.buttonDec){
					for(int i = 0; i < tempNumber.length(); i++)
						if(tempNumber.charAt(i) == '.')
							operDec = true;
					
					//если точки нет, то добавляем
					if(!operDec){
						if(tempNumber.isEmpty()){
							tempNumber += "0";
							
							isStartCopyFuck = true;
						}
						
						tempNumber += ".";
						operDec = true;
						posDecimal = tempNumber.length()-1;
						
						display.setText(tempNumber);
					}
				}else	
					isStart = true;
				
				isStartCopy = true;
				
				if(isResult && !tempNumber.isEmpty())
					if(view.getId() == R.id.buttonSqr || view.getId() == R.id.buttonDec){
						if(!operAdd && !operSub && !operMul && !operDiv){
							result = Double.parseDouble(tempNumber);
							isResult = false;
						}
					}
				
				if(view.getId() == R.id.buttonDelete)
					if(!tempNumber.isEmpty())
						if(!operAdd && !operSub && !operMul && !operDiv){
							result = Double.parseDouble(tempNumber);
							//isResult = false;
					}
				
				/*
				 если приложение только запущенно и мы хотим вычислить квадрат числа,
				 то присваиваем числу исходный результат
				 */
				if(view.getId() == R.id.buttonSqr)
					if(isStartCopy && tempNumber.isEmpty())
						tempNumber = Double.toString(result);
				
				//удаление всех данных, обнуление
				if(view.getId() == R.id.buttonC){
					tempNumber = "";
					tempResult = 0;
					result = 0;
					display.setText("");
					
					posDecimal = -1;
					
					operAdd = false;
					operSub = false;
					operMul = false;
					operDiv = false;
					operResult = false;
					operResultDelete = false;
					operResultDeleteFuck = false;
					operDec = false;
					
					isStart = false;
					isStartCopy = false;
					isStartCopyFuck = false;
					isDelete = false;
					isResult = true;
					isDivByZero = false;
				}
				
				//удаление цифры
				if(view.getId() == R.id.buttonDelete){
					if(isStartCopy && !isResult)
						if(!operAdd && !operMul && !operDiv && !operSub){
						
						tempNumber = Double.toString(result);
						
						//если число заканачивается на ".0", то удаляем
						if(tempNumber.charAt(tempNumber.length()-1) == '0' && 
						   tempNumber.charAt(tempNumber.length()-2) == '.'){
							tempDelete = tempNumber.substring(0, tempNumber.length()-2);
							tempNumber = tempDelete;
						}
						
						isResult = true;
					}else
						isResult = true;
					
					if(isResult && !tempNumber.isEmpty()){
						isDelete = true;
						
						tempDelete = tempNumber.substring(0, tempNumber.length()-1);
						tempNumber = tempDelete;
						display.setText(tempNumber);
					}	
				}
				
				//квадрат числа
				if(view.getId() == R.id.buttonSqr && !tempNumber.isEmpty()){
					numberCopy = new BigDecimal(""+tempNumber);
					resultCopy = numberCopy.multiply(numberCopy);
					
					tempResult = resultCopy.doubleValue();
					
					tempNumber = Double.toString(tempResult);
					
					if(tempNumber.charAt(tempNumber.length()-1) == '0' && 
							   tempNumber.charAt(tempNumber.length()-2) == '.')
									operResultDeleteFuck = true;
					
					result = tempResult;
					
					if(operResultDeleteFuck){
						display.setText("" + (int)result);
						operResultDeleteFuck = false;
					}else
						display.setText("" + result);
					
					tempNumber = Double.toString(result);
					
					if(tempNumber.charAt(tempNumber.length()-1) == '0' && 
							   tempNumber.charAt(tempNumber.length()-2) == '.'){
								tempDelete = tempNumber.substring(0, tempNumber.length()-2);
								tempNumber = tempDelete;
							}
				}	
			}
			
		}
		
		if(event.getAction() == MotionEvent.ACTION_UP){}

		return false;
	}
}