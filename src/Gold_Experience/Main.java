package Gold_Experience;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Main extends JFrame
{
	int LastTime;
	String Username;
	int number=26;
	int num=0;//读取的单词个数
	int chance=0;
	int time=0;
	int mark=0;
	int zero=0;
	int[] arr=new int[8];
	String question=new String();
	StringBuilder Question=new StringBuilder();
	String puzzle;
	
	public void refresh(int n) throws IOException
	{
		File file=new File("record/level"+n+"name.txt");
		file.delete();
		file.createNewFile();
		FileOutputStream out=new FileOutputStream(file,true);
        StringBuffer sb=new StringBuffer();
        sb.append(Username);
        out.write(sb.toString().getBytes("utf-8"));
        out.close();
        
        file=new File("record/level"+n+"num.txt");
		file.delete();
		file.createNewFile();
		out=new FileOutputStream(file,true);
        sb=new StringBuffer();
        sb.append(mark);
        out.write(sb.toString().getBytes("utf-8"));
        out.close();
        
        file=new File("record/level"+n+"time.txt");
		file.delete();
		file.createNewFile();
		out=new FileOutputStream(file,true);
        sb=new StringBuffer();
        sb.append(LastTime);
        out.write(sb.toString().getBytes("utf-8"));
        out.close();
	}
	public void dig(int n)//挖单词的空
	{
		for(int i=0;i<=7;i++)
			arr[i]=0;
		for(int i=0;i<n+1;)
		{
			int ran=(int) (Math.random()*(n+4));
			if(arr[ran]!=1)
			{
				arr[ran]=1;
				i++;
			}
		}
	}
	public void swap(String a,String b)
	{
		String temp=a;
		a=b;
		b=temp;
	}
	public Box box(int width,int height,Component x)//构建Box
	{
		Box b=Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(width));
        b.add(Box.createVerticalStrut(height));
        b.add(x);
        b.add(Box.createHorizontalGlue());
        b.add(Box.createVerticalGlue());
        return b;
	}
	public void login()//登录界面
	{
		JLabel Title=new JLabel("VocEnhancer");
		JLabel UserName=new JLabel("Username:");
		JTextField User=new JTextField(10);
		JButton Confirm=new JButton("Confirm");
		JButton Exit=new JButton("    Exit    ");
		
		Title.setFont(new Font(null, Font.PLAIN, 40));
		UserName.setFont(new Font(null, Font.PLAIN, 20));
		User.setFont(new Font(null, Font.PLAIN, 20));
		User.setText("Six-pack python");
		
		GroupLayout layout=new GroupLayout(this.getContentPane());
		getContentPane().setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup();
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup().addComponent(UserName));
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup().addComponent(Title).addComponent(User).addComponent(Confirm).addComponent(Exit));
		hGroup.addGap(5);
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup();
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(Title));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(UserName).addComponent(User));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(Confirm));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(Exit));
		vGroup.addGap(5);
		layout.setVerticalGroup(vGroup);
	
		Confirm.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Username=User.getText();
				remove(Exit);
				remove(Confirm);
				remove(User);
				remove(UserName);
				remove(Title);
				choice();
			}
		});
		
		Exit.addActionListener(new ActionListener() //退出功能
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
	}
	public void choice()//选择界面
	{
		
		JLabel Title=new JLabel("Six-pack python are WATCHING YOU !!!");
		JButton game_enter,high_look;
		game_enter=new JButton("challenge python");
		high_look=new JButton("   highest score   ");
		Title.setFont(new Font(null, Font.PLAIN, 40));
		
		Box b1=Box.createHorizontalBox();
        b1.add(Box.createVerticalStrut(200));
        b1.add(game_enter);
        b1.add(Box.createHorizontalStrut(60));
        b1.add(high_look);
        b1.add(Box.createHorizontalGlue());
        b1.add(Box.createVerticalGlue());
		
		GroupLayout layout=new GroupLayout(this.getContentPane());
		getContentPane().setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup();
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup().addComponent(Title).addComponent(b1));
		hGroup.addGap(5);
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup();
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(Title));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(b1));
		vGroup.addGap(5);
		layout.setVerticalGroup(vGroup);
		game_enter.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				remove(Title);
				remove(b1);
				levelSelect();
			}
		});
		high_look.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				remove(Title);
				remove(b1);
				show_highest(); 
			}
		});
		pack();
	}
	public void levelSelect()//关卡选择界面
	{
		JLabel Title=new JLabel("Choice your level");
		Title.setFont(new Font(null, Font.PLAIN, 40));
		JButton level1=new JButton("Level 1");
		JButton level2=new JButton("Level 2");
		JButton	level3=new JButton("Level 3");
		JButton returnB=new JButton("Return");
		
		Box b1=box(0, 60, Title);
        Box b2=box(0, 60, level1);
        Box b3=box(0, 60, level2);
        Box b4=box(0, 60, level3);
        Box b5=box(0, 60, returnB);
		
		GroupLayout layout=new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup();
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup().addComponent(b1).addComponent(b2).addComponent(b3).addComponent(b4).addComponent(b5));
		hGroup.addGap(5);
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup();
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(b1));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(b2));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(b3));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(b4));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(b5));
		vGroup.addGap(5);
		layout.setVerticalGroup(vGroup);
		
		level1.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				remove(b1);
				remove(b2);
				remove(b3);
				remove(b4);
				remove(b5);
				try 
				{
					gaming(1);
				} 
				catch (InterruptedException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		level2.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				remove(b1);
				remove(b2);
				remove(b3);
				remove(b4);
				remove(b5);
				try 
				{
					gaming(2);
				} 
				catch (InterruptedException e2) 
				{
					e2.printStackTrace();
				}
			}
		});
		level3.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				remove(b1);
				remove(b2);
				remove(b3);
				remove(b4);
				remove(b5);
				try 
				{
					gaming(3);
				} 
				catch (InterruptedException e3) 
				{
					e3.printStackTrace();
				}
			}
		});
		returnB.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				remove(b1);
				remove(b2);
				remove(b3);
				remove(b4);
				remove(b5);
				choice();
			}
		});
		pack();
	}
	public void show_highest()//最高分；
	{
	        String level1name="",level2name="",level3name="";
	        String level1n="",level2n="",level3n="";
	        String level1time="",level2time="",level3time="";
	        try 
	        (
	        	FileReader reader = new FileReader("record/level1name.txt");
	            BufferedReader br = new BufferedReader(reader)
	        ) 
	        {
	            level1name+=br.readLine();
	            br.close();
				reader.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        try 
	        (
	        	FileReader reader = new FileReader("record/level2name.txt");
		        BufferedReader br = new BufferedReader(reader)
		    ) 
	        {
		            level2name+=br.readLine();
		            br.close();
					reader.close();
		    } 
	        catch (IOException e) 
	        {
		        e.printStackTrace();
		    }
	        try 
	        (	
	        	FileReader reader = new FileReader("record/level3name.txt");
	            BufferedReader br = new BufferedReader(reader)
	        ) 
	        {
	            level3name+=br.readLine();
	            br.close();
				reader.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        try 
	        (
        		FileReader reader = new FileReader("record/level1num.txt");
	             BufferedReader br = new BufferedReader(reader)
	        ) 
	        {
	            level1n+=br.readLine();
	            br.close();
				reader.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        try 
	        (
        		FileReader reader = new FileReader("record/level2num.txt");
	            BufferedReader br = new BufferedReader(reader)
	        ) 
	        {
	            level2n+=br.readLine();
	            br.close();
				reader.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        try 
	        (
        		FileReader reader = new FileReader("record/level3num.txt");
	            BufferedReader br = new BufferedReader(reader)
	        ) 
	        {
	            level3n+=br.readLine();
	            br.close();
				reader.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        try 
	        (
        		FileReader reader = new FileReader("record/level1time.txt");
	            BufferedReader br = new BufferedReader(reader)
	        ) 
	        {
	            level1time+=br.readLine()+"s";
	            br.close();
				reader.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        try 
	        (
        		FileReader reader = new FileReader("record/level2time.txt");
	            BufferedReader br = new BufferedReader(reader)
	        ) 
	        {
	            level2time+=br.readLine()+"s";
	            br.close();
				reader.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        try 
	        (
        		FileReader reader = new FileReader("record/level3time.txt");
	            BufferedReader br = new BufferedReader(reader)
	        ) 
	        {
	            level3time+=br.readLine()+"s";
	            br.close();
				reader.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        JLabel t1=new JLabel(level1name);
	        JLabel t2=new JLabel(level1n);
	        JLabel t3=new JLabel(level2name);
	        JLabel t4=new JLabel(level2n);
	        JLabel t5=new JLabel(level3name);
	        JLabel t6=new JLabel(level3n);
	        JLabel t7=new JLabel(level1time);
	        JLabel t8=new JLabel(level2time);
	        JLabel t9=new JLabel(level3time);
	        JLabel Name=new JLabel("Name");
	        JLabel Score=new JLabel("Score");
	        JLabel tim=new JLabel("Time");
	        JLabel l1=new JLabel("Level 1");
	        JLabel l2=new JLabel("Level 2");
	        JLabel l3=new JLabel("Level 3");
	        JButton btt=new JButton("return");
	        
	        GroupLayout layout=new GroupLayout(this.getContentPane());
			getContentPane().setLayout(layout);
			
			Box b1=box(10, 10, Name);
	        Box b2=box(10, 10, Score);
	        Box b3=box(10, 10, l1);
	        Box b4=box(10, 10, l2);
	        Box b5=box(10, 10, l3);
	        Box n1=box(10, 10, t1);
	        Box s1=box(10, 10, t2);
	        Box n2=box(10, 10, t3);
	        Box s2=box(10, 10, t4);
	        Box n3=box(10, 10, t5);
	        Box s3=box(10, 10, t6);
	        Box tm1=box(10, 10, t7);
	        Box tm2=box(10, 10, t8);
	        Box tm3=box(10, 10, t9);
	        Box rt=box(10, 10, btt);
	        Box timm=box(10, 10, tim);
	        
			GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup();
			hGroup.addGap(5);
			hGroup.addGroup(layout.createParallelGroup().addComponent(rt).addComponent(b3).addComponent(b4).addComponent(b5));
			hGroup.addGap(5);
			hGroup.addGroup(layout.createParallelGroup().addComponent(b1).addComponent(n1).addComponent(n2).addComponent(n3));
			hGroup.addGap(5);
			hGroup.addGroup(layout.createParallelGroup().addComponent(b2).addComponent(s1).addComponent(s2).addComponent(s3));
			hGroup.addGap(5);
			hGroup.addGroup(layout.createParallelGroup().addComponent(timm).addComponent(tm1).addComponent(tm2).addComponent(tm3));
			hGroup.addGap(5);
			layout.setHorizontalGroup(hGroup);
			
			GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup();
			vGroup.addGap(20);
			vGroup.addGroup(layout.createParallelGroup().addComponent(rt).addComponent(b1).addComponent(b2).addComponent(timm));
			vGroup.addGap(20);
			vGroup.addGroup(layout.createParallelGroup().addComponent(b3).addComponent(n1).addComponent(s1).addComponent(tm1));
			vGroup.addGap(20);
			vGroup.addGroup(layout.createParallelGroup().addComponent(b4).addComponent(n2).addComponent(s2).addComponent(tm2));
			vGroup.addGap(20);
			vGroup.addGroup(layout.createParallelGroup().addComponent(b5).addComponent(n3).addComponent(s3).addComponent(tm3));
			vGroup.addGap(20);
			layout.setVerticalGroup(vGroup);
			
			btt.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					remove(b1);
					remove(b2);
					remove(b3);
					remove(b4);
					remove(b5);
					remove(n1);
					remove(n2);
					remove(n3);
					remove(s1);
					remove(s2);
					remove(s3);
					remove(rt);
					remove(timm);
					remove(tm1);
					remove(tm2);
					remove(tm3);
					choice();
				}
			});
			pack();
	}
	public void gaming(int n) throws InterruptedException//游戏界面
	{
		String flip="flip";
		number=26;
		num=0;//读取的单词个数
		chance=n+9;
		time=0;
		mark=0;
		JLabel Chances=new JLabel("Chance: "+chance);
		JLabel Marks=new JLabel("Marks: "+mark+" Points");
		JLabel Time=new JLabel("Time Elapsed: "+time+" Secs");
		JLabel Words=new JLabel("?????");
		JLabel Guess=new JLabel("Guess: ");
		JLabel Remain=new JLabel("Remaining words: "+number);
		JButton Confirm=new JButton("Confirm");
		JTextField text=new JTextField();
		String[] que=new String[27];
		String[] can= {"/0","killer/ql1.txt","killer/ql2.txt","killer/ql3.txt"};//载入题库
		String filen=can[n];
		
		try 
		{
			FileReader fr = new FileReader(filen);
			BufferedReader bf = new BufferedReader(fr);
			String str;
			// 按行读取字符串
			while ((str = bf.readLine()) != null) 
			{
				que[num++]=str;
			}
			bf.close();
			fr.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		int ran=(int) (Math.random()*number+1);
		question=que[ran];
		Question=new StringBuilder(question);
		dig(n);
		for(int i=0;i<=7;i++)
		{
			if(arr[i]==1)
				Question.replace(i, i+1, "_");
		}
		puzzle=""+Question;
		Words.setText(puzzle);
		
		Box chancesBox=box(10, 10, Chances);
		Box marksBox=box(10, 10, Marks);
		Box timeBox=box(10, 10, Time);
		Box wordsBox=box(10, 10, Words);
		Box guessBox=box(10, 10, Guess);
		Box textBox=box(10, 10, text);
		Box remainBox=box(10, 10, Remain);
		Box conBox=box(10, 10, Confirm);
		
		GroupLayout layout=new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup();
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup().addComponent(chancesBox).addComponent(guessBox).addComponent(remainBox));
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup().addComponent(marksBox).addComponent(wordsBox).addComponent(textBox));
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup().addComponent(timeBox).addComponent(conBox));
		hGroup.addGap(5);
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup();
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(chancesBox).addComponent(marksBox).addComponent(timeBox));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(wordsBox));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(guessBox).addComponent(textBox).addComponent(conBox));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(remainBox));
		vGroup.addGap(5);
		layout.setVerticalGroup(vGroup);
		
		Confirm.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				if(text.getText().equals(question))
				{
					mark++;
					
					if(number>=1)
					{
						swap(que[number],que[ran]);
						number--;
						int ran=(int) (Math.random()*number+1);
						question=que[ran];
						Question=new StringBuilder(question);
						dig(n);
						for(int i=0;i<=7;i++)
						{
							if(arr[i]==1)
								Question.replace(i, i+1, "_");
						}
						puzzle=""+Question;
						Words.setText(puzzle);
						Marks.setText("Marks: "+mark+" Points");
						Remain.setText("Remaining words: "+number);
					}
					else
					{
						remove(textBox);
						remove(conBox);
						remove(remainBox);
						remove(guessBox);
						remove(wordsBox);
						remove(timeBox);
						remove(marksBox);
						remove(chancesBox);
						LastTime=time;
						try 
						{
							end(n,1);
						} 
						catch (IOException e1) 
						{
							e1.printStackTrace();
						}
					}
				}
				else
				{
					chance--;
					if(chance<=zero)
					{
						remove(textBox);
						remove(conBox);
						remove(remainBox);
						remove(guessBox);
						remove(wordsBox);
						remove(timeBox);
						remove(marksBox);
						remove(chancesBox);
						try 
						{
							end(n,0);
						} 
						catch (IOException e1) 
						{
							e1.printStackTrace();
						}
					}
					if(text.getText().equals(flip))
					{
						int ran=(int) (Math.random()*number+1);
						question=que[ran];
						Question=new StringBuilder(question);
						dig(n);
						for(int i=0;i<=7;i++)
						{
							if(arr[i]==1)
								Question.replace(i, i+1, "_");
						}
						puzzle=""+Question;
						Words.setText(puzzle);
						Marks.setText("Marks: "+mark+" Points");
						Remain.setText("Remaining words: "+number);
					}
					Chances.setText("Chance: "+chance);
				}
			}
		});
		pack();

		class Process implements Runnable 
		{
		    @Override
		    public void run() 
		    {
		        for (int i = 0; i < 1000; i++) 
		        {
		        	++time;
		        	LastTime=time;
		        	Time.setText("Time Elapsed: "+time+" Secs");
		            try
		            {
		                Thread.sleep(1000);
		            } 
		            catch (InterruptedException e)
		            {
		                e.printStackTrace();
		            }
		            
		        }
		    }
		}
		Process aProcess=new Process();
		Thread thread = new Thread(aProcess);
        thread.setName("线程Process");
        thread.start();
	}
	public void end(int n,int m) throws IOException//胜利界面
	{
		JLabel title;
		if(m==1)
			title=new JLabel("Winner Winner Chicken Dinner");
		else
			title=new JLabel("You get nothing!You are lose!");
		title.setFont(new Font(null, Font.PLAIN, 40));
		JLabel name=new JLabel("Username: "+Username);
		JLabel score=new JLabel("Mark: "+mark+" Points");
		JLabel time=new JLabel("Time: "+LastTime+" Secs");
		JButton reButton=new JButton("Return");
		int hnum=0;
		int htime=0;
		String[] numlist= {"/0","record/level1num.txt","record/level2num.txt","record/level3num"};
		String[] timelist= {"/0","record/level1time.txt","record/level2time.txt","record/level3time"};
		String numfile=numlist[n];
		String timefile=timelist[n];
		
		try 
		{
			FileReader fr = new FileReader(numfile);
			BufferedReader bf = new BufferedReader(fr);
			hnum=Integer.parseInt(bf.readLine());
			bf.close();
			fr.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			FileReader fr = new FileReader(timefile);
			BufferedReader bf = new BufferedReader(fr);
			htime=Integer.parseInt(bf.readLine());
			bf.close();
			fr.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		
		if(hnum<mark)
		{
			refresh(n);
		}
		else if(hnum==mark)
		{
			if(htime>LastTime)
			{
				refresh(n);
			}
		}
		
		Box titleBox=box(0, 20, title);
		Box nameBox=box(0, 20, name);
		Box scoreBox=box(0, 20, score);
		Box timeBox=box(0, 20, time);
		Box reBox=box(0, 20, reButton);
		
		GroupLayout layout=new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup();
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup().addComponent(titleBox).addComponent(nameBox).addComponent(scoreBox).addComponent(timeBox).addComponent(reBox));
		hGroup.addGap(5);
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup();
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(titleBox));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(nameBox));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(scoreBox));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(timeBox));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(reBox));
		vGroup.addGap(5);
		layout.setVerticalGroup(vGroup);
		pack();
		
		reButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				remove(reBox);
				remove(timeBox);
				remove(scoreBox);
				remove(nameBox);
				remove(titleBox);
				choice();
			}
		});
	}
    public static void main(String[] args) throws InterruptedException 
    {
    	Main VocEnhancer=new Main();
    	VocEnhancer.setVisible(true);
    	VocEnhancer.setTitle("VocEnhancer");
    	VocEnhancer.login();
    	VocEnhancer.pack();
    } 
}

