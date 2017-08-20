package com.mohit.resume.server;

import com.mohit.resume.client.GreetingService;
import com.mohit.resume.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}

/**
 * 		<!-- START SINGLE EXPERIENCE DESIGN AREA -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="experience-grid clearfix">
							<div class="image-holder">
								<div class="chid-pernt">
									<div class="child">    
										<i class="fa fa-bookmark"></i>
									</div>
								</div>
							</div>
							<div class="text-box">
								<h3>AVNET SERVICES, CHENNAI, INDIA</h3>
								<h4>Graphics Designer <span>(04/05/2012 - 01/05/2013)</span></h4>
								<p class="short-des wow fadeInUp" data-wow-delay=".2s">Lorem ipsum dolor sit amet, consec tetuer adipi scing elit. consect etuer adipi scing elit Lorem ipsum dolor sit amet, conse ctetuer adipiscing elit. Lorem ipsum dolor sit amet consec tetuer.</p>
							</div>
						</div>
					</div>
					<!-- / END SINGLE EXPERIENCE DESIGN AREA -->
						<!-- START SINGLE EXPERIENCE DESIGN AREA -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="experience-grid clearfix">
							<div class="image-holder">
								<div class="chid-pernt">
									<div class="child">    
										<i class="fa fa-bookmark"></i>
									</div>
								</div>
							</div>
							<div class="text-box">
								<h3>AVNET SERVICES, CHENNAI, INDIA</h3>
								<h4>Graphics Designer <span>(04/05/2012 - 01/05/2013)</span></h4>
								<p class="short-des wow fadeInUp" data-wow-delay=".2s">Lorem ipsum dolor sit amet, consec tetuer adipi scing elit. consect etuer adipi scing elit Lorem ipsum dolor sit amet, conse ctetuer adipiscing elit. Lorem ipsum dolor sit amet consec tetuer.</p>
							</div>
						</div>
					</div>
					<!-- / END SINGLE EXPERIENCE DESIGN AREA -->
 * 
 * 
 * **/
