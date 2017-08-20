/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mohit.resume.server;

// [START simple_includes]
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// [END simple_includes]
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MailServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//      String type = req.getParameter("type");
      System.out.println("In Post - Sending mail...");
      
          resp.getWriter().print("Sending simple email.");
          sendSimpleMail(req,resp);
        
    
  }
  
  private void sendSimpleMail(HttpServletRequest req, HttpServletResponse resp) {
    // [START simple_example]
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    try {
      Message msg = new MimeMessage(session);
      
      String fromName="Mohit-ResumeAgent ";
      String fromEmail="mohitkhakharia@gmail.com";
    
      String subject=req.getParameter("subject");
      String content=req.getParameter("content");
      resp.getWriter().print("Subject -"+subject+" Content - "+ content);
      msg.setFrom(new InternetAddress(fromEmail, fromName));
      
      String customTo=req.getParameter("customTo");
      String customToName=req.getParameter("customToName");
      if(customTo!=null && customTo!=""){
    	  String toName=customToName;
	      String toEmail=customTo;
	      
	      msg.addRecipient(Message.RecipientType.TO,
                  new InternetAddress(toEmail,toName));
	      
	      System.out.println("Sending a mail to -"+toEmail);
	      
      }else{
	      String toName1="Mohit Arvind Khakharia";
	      String toEmail1="mohitakhakharia@gmail.com";
	      
	      
	      msg.addRecipient(Message.RecipientType.TO,
                  new InternetAddress(toEmail1,toName1));
		  System.out.println("Sending a mail to -"+toEmail1);
      }
      
      
      msg.setSubject(subject);
      msg.setText(content);
      Transport.send(msg);
      
      System.out.println("-----MAIL SENT-------");
    } catch (AddressException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
    	e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
    	e.printStackTrace();
    } catch (IOException e) {
		e.printStackTrace();
	}
  }

}