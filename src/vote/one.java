package vote;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
class Register {
	public static void send(String from, String password, String to, String sub, String msg) {
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			// send message
			Transport.send(message);
			// System.out.println("OTP sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	int r;
	String aadhar;

	void Authentifier(String uadhar, String Email) {
		Random rn = new Random();
		r = 1000 + rn.nextInt(8999);
		aadhar = uadhar;
		String se = String.valueOf(r);

		send("example@gmail.com", "pass", Email, "OTP", se);

	}

	String Otpreturner(String k) {
		int otp = Integer.parseInt(k);

		if (otp == r)
			return aadhar;
		else
			return "0";

	}

}

class One extends Frame {
	private static Scanner ch;
	//private static Scanner ch2;
	//private static Scanner sc;
	private static Scanner sq1;

	
	One(){  
        addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  }
	static void CreateNominee(String nom) throws IOException // used to create nominee
	{
		FileWriter ffw = new FileWriter("Nominees.txt", true);
		ffw.write(nom + "\r\n");
		// System.out.println("UPDATED DETAILS SUCCESFULLY");
		ffw.close();
	}

	static String[] ShowNominee() throws IOException // to display nominee
	{
		FileReader ffr = new FileReader("Nominees.txt");
		sq1 = new Scanner(ffr);
		String Lists[] = new String[1000];
		int i = 0;
		while (sq1.hasNext()) {

			// System.out.println(String.valueOf(i) + "\t\t\t\t" + (Lists[i] =
			// sq.nextLine()));
			Lists[i] = sq1.nextLine();
			i++;
		}
		// if (i == 0)
		// System.out.println("no nominees");
		ffr.close();
		return Lists;
	}

	static int CheckVoter(String ss, int n) throws IOException {// to check voter repetition and presense
		int f = 0, i = 0, countt = 0;
		String check[] = new String[100];
		FileReader fr = new FileReader("Data.txt");
		ch = new Scanner(fr);

		while (ch.hasNext()) {
			check[i] = ch.nextLine();
			// System.out.println(check[i]);
			i++;
			countt++;

		}
		fr.close();

		for (i = 0; i < countt; i++) {
			if (check[i].equals(ss))
				f = 1;
		}
		if (n == 1) {

			if (f == 1)
				return 0;
			else
				return 1;
		} else if (n == 2) {
			if (f == 1)
				return 1;
			else
				return 0;
		}
		return 0;
	}

	static String vaadhar;
	static String NomineeList[] = new String[1000];
	static	JTable ct;
	static int sel; // for selecting between voting and regis

	
	
	public static void main(String args[]) throws IOException {
		
		Register obj = new Register();

		// switch (i) {
		// case 1: {

		FileWriter fw = new FileWriter("Data.txt", true);

		Frame fmain = new Frame("VOTE");
		fmain.setVisible(true);
		fmain.setSize(300, 300);
		fmain.setLayout(null);
		Button b11 = new Button("vote");
		b11.setBounds(80, 100, 100, 20);
		fmain.add(b11);
		Button b22 = new Button("Register");
		b22.setBounds(80, 140, 100, 20);
		fmain.add(b22);
		Button b33 = new Button(" EnterNOm");
		b33.setBounds(80, 180, 100, 20);
		fmain.add(b33);
		Button b44 = new Button("results");
		b44.setBounds(80, 220, 100, 20);
		fmain.add(b44);
		Button b55 = new Button("Exit");
		b55.setBounds(80,260,100,20);
		fmain.add(b55);

		Frame f1 = new Frame("Authentifier");
		Frame f2 = new Frame("Otp login");

		// for f3 frame
		JFrame f3 = new JFrame("VOTER");
		f3.setSize(1000,1000);
		f3.setLayout(null);

		JButton bt = new JButton("submit");
		bt.setBounds(300, 700, 80, 20);
		f3.add(bt);
		JTextField tf = new JTextField("Enter");
		tf.setBounds(80, 700, 40, 20);
		f3.add(tf);
		JLabel lb = new JLabel("Enter choice of the nominee");
		lb.setBounds(80, 740, 180, 20);
		f3.add(lb);
		//for thanks for voting
		Font font = new Font("Courier", Font.BOLD, 25);
		Frame f6 = new Frame("Thanks");
		
		f6.setSize(1200,1000);
		f6.setLayout(null);
		Label jlb = new Label("Thanks For Voting...");
		
		jlb.setBounds(600,100,800,100);
		jlb.setFont(font);
		
		f6.add(jlb);

	
		Frame f7 = new Frame("OOPS");
		
		f7.setSize(1200,1000);
		f7.setLayout(null);
		Label jlb1 = new Label("Sorry,Wrong choice..");
		jlb1.setFont(font);
		jlb1.setBounds(150,300,400,100);
		
		
        f7.add(jlb1);
        
        JFrame jfinale = new JFrame("ADMIN");
       
        JButton jbt = new JButton("submit");
       
        

		// frame for nominee

		Frame f4 = new Frame("NOMINNE ADDER");
		Label name = new Label("Enter name");

		Label pname = new Label("Enter party name");

		Label age = new Label("Enter age");

		TextField ttf = new TextField("enter");

		TextField ttf1 = new TextField("enter");

		TextField ttf2 = new TextField("enter");

		Label d = new Label();

		Button bbt = new Button("submit");
		// bbt.setBounds(80,200,40,20);
		// f4.add(bbt);

		
		
		
		
		//Frame for results
		
		
		JFrame f5 = new JFrame("RESULTS");
		
		
		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fmain.dispose();
				f1.setVisible(true);

				sel = 2;
			}
		});

		b22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fmain.dispose();
				f1.setVisible(true);
				sel = 1;
			}
		});
		JTextField jtf = new JTextField("enter");
		JTextField jtf1 = new JTextField("enter");
		b33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fmain.dispose();
				jfinale.setVisible(true);
				jfinale.setSize(800,800);
				jfinale.setLayout(null);
				 JLabel labe = new JLabel("user");
			        labe.setBounds(80,100,40,20);
			        jfinale.add(labe);
			        JLabel labe1 = new JLabel("password");
			        labe1.setBounds(80,140,60,20);
			        jfinale.add(labe1);
			        
			        jtf.setBounds(300,100,40,20);
			        jfinale.add(jtf);
			        
			        jtf1.setBounds(300,140,40,20);
			        jfinale.add(jtf1);
			        jbt.setBounds(80,180,100,20);
			        jfinale.add(jbt);
			        
			       
			        		
			        
			}
		});

		b44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             try {
            	
            	 fmain.dispose();
            	 FileReader finalread = new FileReader("final.txt");
            	 Scanner A = new Scanner(finalread);
            	 int ch=0,x=0,y=0;
            	 int p=5;
            	 NomineeList = ShowNominee();
            	 while(NomineeList[ch]!=null)
            	 {
            		 ch++;
            	 }
            	 String ss[][] = new String[ch][5];
            	while(A.hasNext()) 
            		{y=0;
            			while(p!=0)
            			{
            				ss[x][y] = A.next();
            				
            				y++;
        
            				p--;
            			}x++;p=5;
 
            			
            		}
            	
            	String cloumn [] = {"regno","name","partyname","age","votes"};
            	f5.setVisible(true);
            	f5.setSize(1000,1000);
            	f5.setLayout(null);
            
            	JLabel jl = new JLabel("regno");
            	jl.setBounds(80,60,40,20);
            	f5.add(jl);
            	JLabel jl1 = new JLabel("name");
            	jl1.setBounds(240,60,800,25);
            	f5.add(jl1);
            	JLabel jl2 = new JLabel("partyname");
            	jl2.setBounds(400,60,800,25);
            	f5.add(jl2);
            	JLabel jl3 = new JLabel("age");
            	jl3.setBounds(560,60,800,25);
            	f5.add(jl3);
            	JLabel jl4 = new JLabel("votes");
            	jl4.setBounds(720,60,800,25);
            	f5.add(jl4);
            	JTable lbl = new JTable(ss,cloumn);
            	lbl.setBounds(80,80,800,800);
            	f5.add(lbl);
            	
            	
            A.close();
             }catch(Exception o) {}
              
			}
		});
		
		b55.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eee)
			{fmain.dispose();
				
			}
		}
		);
		
		
		
		
		
		
		
		
		
		
		//new
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a)
			{  jfinale.dispose();
				String g = "admin";
	        String k = "password";
	        if(g.equals(jtf.getText())&&k.equals(jtf1.getText()))
	        {
	        	
	        
				f4.setVisible(true);
				f4.setLayout(null);
				f4.setSize(800, 800);
				name.setBounds(80, 100, 100, 20);
				f4.add(name);
				pname.setBounds(80, 140, 200, 20);
				f4.add(pname);
				age.setBounds(80, 180, 100, 20);
				f4.add(age);
				ttf.setBounds(300, 100, 200, 20);
				f4.add(ttf);
				ttf1.setBounds(300, 140, 200, 20);
				f4.add(ttf1);
				ttf2.setBounds(300, 180, 200, 20);
				f4.add(ttf2);
				bbt.setBounds(80, 200, 40, 20);
				f4.add(bbt);
				d.setBounds(80, 250, 200, 20);
				f4.add(d);
				 
			        	
			}else
			{
				f7.setVisible(true);
				jfinale.dispose();
				jlb1.setText("Wrong details");
				try{java.util.concurrent.TimeUnit.SECONDS.sleep(2);
				
				fmain.setVisible(true);
			}catch(Exception eeeee) {}}
	        }
		});
		
		
		
		// For Registration

		f1.setSize(800, 800);
		f1.setLayout(null);
		Label Adhaar = new Label("Adhaar");
		Adhaar.setBounds(80, 100, 100, 20);
		f1.add(Adhaar);
		Label Mail = new Label("Mail");
		Mail.setBounds(80, 140, 100, 20);
		f1.add(Mail);

		TextField tf1 = new TextField("enter");
		tf1.setBounds(300, 100, 100, 20);
		f1.add(tf1);
		TextField tf2 = new TextField("enter");
		tf2.setBounds(300, 140, 300, 20);
		f1.add(tf2);

		Button b1 = new Button("Submit");
		b1.setBounds(300, 180, 100, 20);
		f1.add(b1);
		Button b2 = new Button("Submit");
		TextField tf3 = new TextField("enter");
		Label ll = new Label();
		Label ans = new Label();
		ans.setBounds(80,180,100,20);
		f1.add(ans);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{obj.Authentifier(tf1.getText(), tf2.getText());
				ans.setText("pleasewait");
				java.util.concurrent.TimeUnit.SECONDS.sleep(2);
				f1.dispose();
				f2.setVisible(true);
				f2.setSize(800, 800);
				f2.setLayout(null);

				Label Otp = new Label("Otp");
				Otp.setBounds(80, 100, 100, 20);
				f2.add(Otp);

				tf3.setBounds(300, 100, 100, 20);
				f2.add(tf3);
				b2.setBounds(80, 120, 100, 20);
				f2.add(b2);
				ll.setBounds(300, 120, 250, 20);
				f2.add(ll);
			}catch(Exception ef) {}}

		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaadhar = obj.Otpreturner(tf3.getText());
				if (vaadhar.equals("0")) {
					try {
						ll.setText("invalid otp");
						java.util.concurrent.TimeUnit.SECONDS.sleep(2);
						f2.dispose();
						ll.setText("");
						fmain.setVisible(true);
					} catch (Exception f) {
					}
				} else {
					try {
						int c = CheckVoter(vaadhar, sel);
						// System.out.println(c);
						if (sel == 1) {
							if (c == 1) {
								fw.write(vaadhar + "\r\n");
								ll.setText("Regsitered Succesfully");
								fw.close();
								java.util.concurrent.TimeUnit.SECONDS.sleep(2);
								f2.dispose();
								ll.setText("");

								fmain.setVisible(true);

							} else {
								ll.setText("already registered");
								java.util.concurrent.TimeUnit.SECONDS.sleep(2);

								f2.dispose();
								ll.setText("");
								fmain.setVisible(true);

								fw.close();

							}
						} else if (sel == 2) {
							if (c == 1) {
								try {
									ll.setText("loginsucces");
									java.util.concurrent.TimeUnit.SECONDS.sleep(2);
									f2.dispose();
									ll.setText("");
									NomineeList = ShowNominee();
									
														
									int s = 0;
									while (NomineeList[s] != null) {
										//ct.add(String.valueOf(s)+"\t"+NomineeList[s]);
										s++;
									}
									 
									
                                    FileReader fread = new FileReader("Nominees.txt");
                                    Scanner sst = new Scanner(fread);
                                    String sp[][] = new String[s][4];
                                    int w=0,t=0,p=4,y=0;
                                   
                                  try {  while(sst.hasNext())
                                       {
                                    	while(p!=0)
                                    	{if(p==4)
                                    		{sp[w][t] =String.valueOf(y);;t++;p--;
                                    		}
                                    	else
                                    	{sp[w][t]=sst.next();
                                    	
                                    	t++;
                                    	p--;
                                    	}
                                    	
                                    	}w++;p=4;t=0;y++;
                                      }
                                  
                                  }catch(Exception eee) {System.out.println(e);}
                                    //System.out.println("hello");
                                    sst.close();
                                    String cl[] = {"regno","name","partyname","age"};

                                	JLabel jl = new JLabel("regno");
                                	jl.setBounds(80,60,40,20);
                                	f3.add(jl);
                                	JLabel jl1 = new JLabel("name");
                                	jl1.setBounds(200,60,800,25);
                                	f3.add(jl1);
                                	JLabel jl2 = new JLabel("partyname");
                                	jl2.setBounds(340,60,800,25);
                                	f3.add(jl2);
                                	JLabel jl3 = new JLabel("age");
                                	jl3.setBounds(460,60,800,25);
                                	f3.add(jl3);
                                    
                                    JTable ct = new JTable(sp,cl);
                                    
                                    ct.setBounds(80, 100, 500, 500);
                            		f3.add(ct);
                                   // System.out.println("OH");

                                    f3.setVisible(true);
                                    f3.setSize(1000,1000);
                                    f3.setLayout(null);
                                   

                            	
                                    
                                    
								

								} catch (Exception eee) {
								}

							} else {
								ll.setText("loginfailedregisterfirst");
								java.util.concurrent.TimeUnit.SECONDS.sleep(2);

								f2.dispose();
								ll.setText("");
								fmain.setVisible(true);

							}
						}
					} catch (Exception p) {
					}
				}
			}
		});

		// f3 frame button bt setting //voting happens

		String Conv[] = new String[1000];

		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter fffw = new FileWriter("Final.txt");
					FileReader Rs = new FileReader("Num.txt");
					int result = 0, get, k = 0, l = 0, m = 0;
					Scanner Ob = new Scanner(Rs);
					int a[] = new int[1000];
					while (NomineeList[m] != null)// reading num values before trunct
					{
						try {
							a[m] = Integer.parseInt(Ob.nextLine());
							m++;
						} catch (Exception e1) {
							a[m] = 0;
							m++;
						}
					}

					Rs.close();
					
					
					get = Integer.parseInt(tf.getText());// enter choice
					if (get < m) {
						result = a[get] + 1;
						a[get] = result;

						// to convert integer to string
						FileWriter Fs = new FileWriter("Num.txt");
						while (NomineeList[k] != null) {
							Conv[k] = String.valueOf(a[k]);
							Fs.write(Conv[k] + "\r\n");
							k++;
						}
						// conversion of int
						while (NomineeList[l] != null) {
							// System.out.println(String.valueOf(k)+ " "+ NomineeList[k] +" "+
							// Conv[k] + "\r\n");
							fffw.write(String.valueOf(l) + "\t" + NomineeList[l] + "\t" + Conv[l] + "\r\n");
							l++;
						}

						fffw.close();
						Fs.close();
						
						
						
						
						f3.dispose();
						f6.setVisible(true);
						java.util.concurrent.TimeUnit.SECONDS.sleep(2);
						f6.dispose();
						fmain.setVisible(true);
					} else {
						
						
						
						f3.dispose(); 
						
						f7.setVisible(true);
						java.util.concurrent.TimeUnit.SECONDS.sleep(2);
						f7.dispose();
						fmain.setVisible(true);
					}
				} catch (Exception ee) {
				}
			}
		});

		// event for nominne add button bbt
		bbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String temp = ttf.getText() + "\t" + ttf1.getText() + "\t" + ttf2.getText();

					CreateNominee(temp);
					d.setText("added succesfully");
					
					java.util.concurrent.TimeUnit.SECONDS.sleep(2);
					f4.dispose();
					fmain.setVisible(true);
				} catch (Exception r) {
				}
			}
		});

	}
}
