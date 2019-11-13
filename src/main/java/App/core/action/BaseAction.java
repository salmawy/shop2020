package App.core.action;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

import org.springframework.beans.factory.BeanFactory;

import App.App;
import App.core.beans.Fridage;
import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;
import javafx.stage.Stage;
public class BaseAction {
	
	private final ResourceBundle serviceBundle = ResourceBundle.getBundle("appResources.myBundel_ar");
	private IBaseService  baseService ;
	private IBaseRetrievalService  baseRetrievalService ;
	private Stage AppStage;
	private BeanFactory springBeanFactory;
	private Season season;
	private Fridage fridage;

	public BaseAction() {
	
		
		baseService=	(IBaseService) App.springBeanFactory.getBean("baseService");
		baseRetrievalService=	(IBaseRetrievalService) App.springBeanFactory.getBean("baseService");
		this.AppStage=App.AppStage;
		this.springBeanFactory=App.springBeanFactory;
		getCurrentSeaon();
		getCurrentFridage();
	
		
	}
	
	private void getCurrentSeaon() {
		
		try {
			 season= this.getBaseRetrievalService().getCurrentSeason();
		} catch (DataBaseException | EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	private void getCurrentFridage() {
		
		try {
			 fridage= (Fridage) this.getBaseRetrievalService().getBean(Fridage.class, 1);
		} catch (Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void loadScene(String className) {
		
		try{Class<?> clazz = Class.forName(className);
		Constructor<?> ctor = clazz.getConstructor(String.class);
		Object object = ctor.newInstance();
		
		
	}catch(Exception e) {
		
		
		e.printStackTrace();
		
		
		
	}}




	public BeanFactory getSpringBeanFactory() {
		return springBeanFactory;
	}


	public void setSpringBeanFactory(BeanFactory springBeanFactory) {
		this.springBeanFactory = springBeanFactory;
	}


	public ResourceBundle getServiceBundle() {
		return serviceBundle;
	}


	public Stage getAppStage() {
		return AppStage;
	}




	public void setAppStage(Stage appStage) {
		AppStage = appStage;
	}




	public IBaseService getBaseService() {
		return baseService;
	}




	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}




	public IBaseRetrievalService getBaseRetrievalService() {
		return baseRetrievalService;
	}




	public void setBaseRetrievalService(IBaseRetrievalService baseRetrievalService) {
		this.baseRetrievalService = baseRetrievalService;
	}




	public String getMessage (String key) {
		return serviceBundle.getString(key);
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Fridage getFridage() {
		return fridage;
	}

	public void setFridage(Fridage fridage) {
		this.fridage = fridage;
	}
	
	
	
	
	
	
	
	

}
