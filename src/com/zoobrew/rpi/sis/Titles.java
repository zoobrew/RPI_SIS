package com.zoobrew.rpi.sis;

public class Titles 
{
	
	static String[] Menus = 
	{
        "Registration Information",
        "Curriculum Information",
        "Graduation Information",
        "Financial Aid Information",
        "Personal Information"
    };
    
    static String[] [] SubMenu = 
    {   //RegistrationItems
    	{"Registation Status",
    	"Register, Add or Drop",
    	"View Schedule"},
    	
    	//CurriculumItems
    	{"Advisor and Curriculum Information",
		"View Grades",
		"VIew Transcript",
		"Request Transcript",
		"Check Transcript Request Status",
		"View Capp Report"},
		
		//GraduationItems
		{"Degree Application Term",
    	"Degree Status",
    	"View Holds",
    	"View Diploma Information"},
    
    	//FinancialItems
    	{"Account Information",
    	"Status of Financial Aid",
    	"View Eligibility",
    	"Award Information",
    	"Laptop Status"},
    	
    	//Personal Information
    	{"Update RPI Alert number",
    	"Change My PIN",
    	"Update My Address",
    	"Update My Emergency Contacts"
    	}
    };
}
