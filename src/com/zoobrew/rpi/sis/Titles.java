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

    static String[] Items = 
    {
        "Item One\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, nulla laborum salvia dreamcatcher fanny pack. Ullamco culpa retro ea, trust fund excepteur eiusmod direct trade banksy nisi lo-fi cray messenger bag. Nesciunt esse carles selvage put a bird on it gluten-free, wes anderson ut trust fund twee occupy viral. Laboris small batch scenester pork belly, leggings ut farm-to-table aliquip yr nostrud iphone viral next level. Craft beer dreamcatcher pinterest truffaut ethnic, authentic brunch. Esse single-origin coffee banksy do next level tempor. Velit synth dreamcatcher, magna shoreditch in american apparel messenger bag narwhal PBR ennui farm-to-table.",
        "Item Two\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. Enim kogi keytar trust fund pop-up portland gentrify. Non ea typewriter dolore deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua pork belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, vegan carles odd future."
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
