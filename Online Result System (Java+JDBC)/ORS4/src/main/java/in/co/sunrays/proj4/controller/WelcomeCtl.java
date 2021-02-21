package in.co.sunrays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.controller.BaseCtl;
import in.co.sunrays.proj4.controller.ORSView;
import in.co.sunrays.proj4.controller.WelcomeCtl;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Welcome functionality Controller. Performs operation for Show Welcome page
 *
 * @author SunilOS
 * @version 1.0
 * 
 */
@WebServlet(name = "WelcomeCtl", urlPatterns = { "/WelcomeCtl" })
public class WelcomeCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(WelcomeCtl.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("WelcomeCtl Method doGet Started");

		ServletUtility.forward(getView(), request, response);

		log.debug("WelcomeCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}

}