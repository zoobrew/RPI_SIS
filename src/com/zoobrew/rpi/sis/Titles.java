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
    	"Change My Thesis/Project Credits",
    	"View Schedule Grid",
    	"View Schedule",
    	"Pre-Pack Order"},
    	
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
    static String [][] MenuHttp = 
    {
    	//RegistrationItems
    	{"https://sis.rpi.edu/rss/bwskrsta.P_RegsStatusDisp",
    	"https://sis.rpi.edu/rss/bwskfreg.P_AltPin",
    	"https://sis.rpi.edu/rss/bwskfreg.P_ChangeCrseOpt",
    	"https://sis.rpi.edu/rss/bwskfcls.p_sel_crse_search",
    	"https://sis.rpi.edu/rss/bwskfshd.P_CrseSchd",
    	"https://sis.rpi.edu/rss/bwskfshd.P_CrseSchdDetl",
    	"http://bookstore.rpi.edu/index.php?module=prepack2"
    	},
    	//CurriculumItems
    	{"https://sis.rpi.edu/rss/bwskgstu.P_StuInfo",
    	"https://sis.rpi.edu/rss/bwskogrd.P_ViewTermGrde",
    	"https://sis.rpi.edu/rss/bwskotrn.P_ViewTermTran",
    	"https://sis.rpi.edu/rss/bwskwtrr.p_disp_transcript_request_type",
    	"https://sis.rpi.edu/rss/bwskwtrr.p_disp_status_of_order",
    	"https://sis.rpi.edu/rss/hwskocap.P_StuSelectCompl",
    	},
    	//GraduationItems
    	{"https://sis.rpi.edu/rss/hwskgrad.P_StuSelectTerm?menu_choice=A",
    	"https://sis.rpi.edu/rss/hwskgrad.P_StuSelectTerm?menu_choice=B",
    	"https://sis.rpi.edu/rss/hwskgrad.P_StuViewHolds",
    	"https://sis.rpi.edu/rss/hwskgrad.P_StuSelectTerm?menu_choice=D"
    	},
    	//FinancialItems
    	{"https://sis.rpi.edu/rss/twbkwbis.P_GenMenu?name=bmenu.P_AcctInfoMnu",
    	"https://sis.rpi.edu/rss/bwrksumm.P_DispSumm",
    	"https://sis.rpi.edu/rss/twbkwbis.P_GenMenu?name=bmenu.P_FACostMnu",
    	"https://sis.rpi.edu/rss/twbkwbis.P_GenMenu?name=bmenu.P_FAAwdMnu",
    	"https://sis.rpi.edu/rss/hwsklptp.P_StuViewLptp",
    	},
    	//Personal Information
    	{"https://sis.rpi.edu/rss/ywemernot.P_Main",
    	"https://sis.rpi.edu/rss/twbkwbis.P_ChangePin",
    	"https://sis.rpi.edu/rss/bwgkogad.P_SelectAtypUpdate",
    	"https://sis.rpi.edu/rss/bwgkoemr.P_SelectEmrgContacts"
    	}
    };
    
    
    
    
    
    
    
    
}
