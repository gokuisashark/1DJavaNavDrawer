package com.example.fish.a1djava;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnrolmentFragment extends Fragment {


    public EnrolmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enrolment, container, false);

        //1st group: EnrolPillar TextView and EnrolPillar Spinner, try not to split
        TextView enrolPillar = (TextView) view.findViewById(R.id.enrolPillar);
        Spinner enrolSpinner = (Spinner) view.findViewById(R.id.pillarSpinner);
        ArrayAdapter<CharSequence> enrolSpinnerAdapter = ArrayAdapter.createFromResource(
                getActivity().getApplicationContext(), R.array.pillars_array,
                android.R.layout.simple_spinner_item);
        enrolSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enrolSpinner.setAdapter(enrolSpinnerAdapter);

        //2nd group: Course1 TextView and Course1 AutoCompleteTextView, don't split
        ArrayAdapter<String> AutoViewAdapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_dropdown_item_1line, MODULES);

        final AutoCompleteTextView course1AutoView = (AutoCompleteTextView)
                view.findViewById(R.id.choosecourse1autoview);
        course1AutoView.setAdapter(AutoViewAdapter);
        course1AutoView.setThreshold(1);
        course1AutoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course1AutoView.showDropDown();
            }
        });

        final AutoCompleteTextView course2AutoView = (AutoCompleteTextView)
                view.findViewById(R.id.choosecourse2autoview);
        course2AutoView.setAdapter(AutoViewAdapter);
        course2AutoView.setThreshold(1);
        course2AutoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course2AutoView.showDropDown();
            }
        });

        final AutoCompleteTextView course3AutoView = (AutoCompleteTextView)
                view.findViewById(R.id.choosecourse3autoview);
        course3AutoView.setAdapter(AutoViewAdapter);
        course3AutoView.setThreshold(1);
        course3AutoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course3AutoView.showDropDown();
            }
        });

        final AutoCompleteTextView course4AutoView = (AutoCompleteTextView)
                view.findViewById(R.id.choosecourse4autoview);
        course4AutoView.setAdapter(AutoViewAdapter);
        course4AutoView.setThreshold(1);
        course4AutoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course4AutoView.showDropDown();
            }
        });

        return view;
    }

    //modules database DO NOT EDIT
    public static final String[] MODULES = new String[]{
            "01.001: Probability & Statistics",
            "01.005: Fifth Row",
            "01.006: 2D Integrative Designettes",
            "01.007: Better Writing Better Opportunities Session A",
            "01.008: Better Writing Better Communications Session B",
            "01.009: Freshmore English Programme",
            "01.010: Freshmore Writing Programme",
            "01.101: Global Health Technology",
            "01.102: Energy Systems and Management",
            "01.102: Energy Systems",
            "01.103: Transportation Technology",
            "01.104: Networked Life",
            "01.105: Design and Manufacturing",
            "01.106: Engineering Management",
            "01.107: Urban Transportation",
            "01.108: Enterprise Systems",
            "01.110: Computational Fabrication",
            "01.111: Culture Formation and Innovative Design",
            "01.112: Machine Learning",
            "01.400: Capstone 1",
            "01.401: Capstone 2",
            "01.501: Research Thesis",
            "01.502: M.Eng Research",
            "01.800: Research Attachment / Internship",
            "01.900: Big D Project I",
            "01.901: Big D Project II",
            "01.902: Intellectual Property (IP) Clinic",
            "01.903: Scientific and Grant Writing",
            "01.904: SUTD Entrepreneurship",
            "01.905: SUTD Private Equity & Fund Raising for Businesses",
            "01.906: Technology Entrepreneurship: From IP to IPO",
            "01.907: Leadership, Teamwork and Personal Branding",
            "01.910: Speaking Focus in the Academic Environment",
            "01.911: Teaching at SUTD: Engaging the Learners",
            "01.XXX: Mapping Subject for Summer Programmes",
            "02.001: World Texts and Interpretations",
            "02.002: World Civilisations and Texts II",
            "02.003: Theorizing Society, the Self, and Culture",
            "02.004: Physics in the 20th Century",
            "02.010: Entrepreneurship Seminar",
            "02.014: Design Management",
            "02.018: History of Chinese Urban Development and Planning",
            "02.019: Sustainability of Ancient Chinese Architecture Design in the Modern World",
            "02.101: Darwin and Design",
            "02.102: The World Since 1400",
            "02.103: Film Studies: History Theory and Practice",
            "02.104: The History of International Development in Asia: The Role of Engineers and Designers",
            "02.105: Sages Through the Ages",
            "02.106: Crime and Punishment: Introduction to Law and Literature",
            "02.107: History of Chinese Urban Development",
            "02.108: Modern China: Pluralism, and Beyond Territoriality",
            "02.109: Ethics of Leadership",
            "02.110: The Chinese Lyrical Tradition: Arts, Literature and Landscape Design",
            "02.111: Photography in Modern Southeast Asian History",
            "02.112: Space and Power",
            "02.113: The Laboratory of the Mind",
            "02.114: The Multicultural Archipelago in History and Story",
            "02.115: Global Shakespeares",
            "02.116: The Word and the World: Introducing Literary Theory",
            "02.118: Film Studies II: Production",
            "02.119: Introduction to Political Philosophy",
            "02.120: History of Traditional Chinese Short Fiction",
            "02.121: The Question of Being",
            "02.122: The Day After Tomorrow: Environmental Apocalypse and Political Mobilization",
            "02.123: Questioning Modernity in Europe and Asia",
            "02.124: The Modern East Asian Nexus – A History",
            "02.125: Normalcy and Deviance: Philosophical Approaches to Sexuality",
            "02.126: Southeast Asia under Japan: Motives, Memoirs and Media",
            "02.127: Satan and His Afterlives in Literature and Film",
            "02.128: Classical Indian Literature and Art",
            "02.129: Shakespeare, Race, and Religion in the Renaissance World",
            "02.130: Slums, Squatters, and Smart Cities: History and Theory of Urban Planning",
            "02.131: Non‐Fiction and Ethnographic Film: Theory and Practice",
            "02.1XX: HASS Mapping Subject Bin 1",
            "02.201: Digital Sociology",
            "02.202: Organisational Processes",
            "02.203: Psychological Approaches to Bilingualism",
            "02.204: Technology and the Self",
            "02.205: Human Development: Theory and Practice",
            "02.206: Ethics and Social Responsibility",
            "02.207: Organizations and People",
            "02.208: Microeconomics",
            "02.209: Social Theories of Urban Life",
            "02.210: Who Gets Ahead? Sociology of Social Networks and Social Capital",
            "02.211: Critical Management Studies",
            "02.212: The Visual Culture of Science and Technology",
            "02.213: How Culture Works",
            "02.214: Magic, Science and Religion",
            "02.215: Political Economy",
            "02.216: Human Behaviour, Technology and Design",
            "02.217: Gender, Sexuality and Society",
            "02.218: Introduction to Psychology",
            "02.219: Rice Cultures: Technology, Society, and Environment in Asia",
            "02.220: “How the Things People Make, Make People”: Material Things in Social Life",
            "02.221: Making Maps I: Introduction to Spatial Analysis, Data Visualisation and Map Design",
            "02.222: Unnatural Disasters",
            "02.223: Morals and Markets",
            "02.224: Sociology of Deviance and Social Control",
            "02.225: Building, Dwelling, Belonging: An Anthropology of Domestic and Vernacular Architecture",
            "02.226: Leaders and Followers",
            "02.227: Maps and Politics",
            "02.2XX: HASS Mapping Subject Bin 2",
            "02.302: Business Culture and Entrepreneurship in China",
            "02.303: The Role of Technology and Design on Growth in China",
            "02.3XX: HASS Mapping Subject Bin 3",
            "03.007: Introduction to Design",
            "10.001: Advanced Math I",
            "10.002: Physics I",
            "10.003: Chemistry: From Atoms to Crystals",
            "10.004: Advanced Math II",
            "10.005: Physics II",
            "10.006: Chemistry and Biology: Natural World",
            "10.007: Modelling the Systems World",
            "10.008: Engineering in the Physical World",
            "10.009: The Digital World",
            "10.010: Introduction to Biology",
            "10.011: Introduction to Physical Chemistry",
            "10.012: Introduction to Biology",
            "20.099: Urban Sketching",
            "20.101: Architecture Core Studio 1",
            "20.102: Architecture Core Studio 2",
            "20.103: Architecture Core Studio 3",
            "20.111: Sustainable Design Option Studio 1",
            "20.112: Sustainable Design Option Studio 2",
            "20.201: Architecture Science & Technology",
            "20.202: Architectural Structure & Enclosure Design",
            "20.203: Architectural Energy Systems",
            "20.211: Introduction to Design Computation",
            "20.212: Digital Design and Fabrication",
            "20.213: Building Information Modeling",
            "20.221: History Theory and Culture 1",
            "20.222: History, Theory and Culture 2",
            "20.223: History, Theory and Culture III: Contemporary architecture between technology, science and culture",
            "20.301: Material Computation: Advanced Topics in Geometry and Matter",
            "20.302: Advanced Topics in Performative Design: Daylight and Electric Lighting",
            "20.303: Urban Analysis",
            "20.304: Urban Housing Typologies",
            "20.305: Conservation Theories & Approaches of Built Heritage",
            "20.306: Integrated Building Design",
            "20.307: Toward Carbon-Neutral Architecture and Urban Design",
            "20.308: Sustainability and Green Design in Urban and Natural Environments",
            "20.309: Sustainability of Ancient Chinese Architecture Design in the Modern World",
            "20.310: The Built Environment and Value",
            "20.311: Advanced Topics in Digital Design and Fabrication",
            "20.312: Social Architecture: Theory and Practice",
            "20.313: Advanced Topics in Performative Design: Urban Sustainability",
            "20.314: Paradigms of Adaption",
            "20.315: Future Material Systems",
            "20.500: Advanced Design & Research Studio",
            "20.501: Sustainable Design Option Studio 3",
            "20.502: Design Computation in Architecture",
            "20.503: Advanced Topics in Performative Design: Daylight and Electric Lighting",
            "20.504: Material Computation: Advanced Topics in Geometry and Matter",
            "20.505: Urban Housing Typologies",
            "20.511: Thesis Preparation",
            "20.512: Thesis",
            "20.513: Building Contract Law & Practice",
            "20.515: Professional Practice II",
            "20.521: Advanced Topics in Digital Design and Fabrication",
            "20.522: Social Architecture: Theory and Practice",
            "20.523: Advanced Topics in Performative Design: Urban Sustainability",
            "20.524: Paradigms of Adaption",
            "20.525: Advanced Topics in Performative Design: Daylight and Electric Lighting",
            "20.526: Urban Housing Typologies",
            "20.527: Material Computation: Advanced Topics in Geometry and Matter",
            "20.528: Future Material Systems",
            "20.590: PhD Pro-Seminar",
            "20.620: Independent Study (Building Technology)",
            "20.621: Independent Study (Design)",
            "20.622: Independent Study (Digital Fabrication of Ceramics)",
            "20.623: Independent Study (Writing and Storytelling for Design and Architecture)",
            "20.624: Independent Study (Composite Panel Fabrication via Graded Knitted Textiles)",
            "20.625: Independent Study (Applying Internet Trend Data to Assess Urban Space Performance)",
            "20.626: Independent Study (Parametric Urban Sensitivity Analysis Between Design and Environmental Impacts)",
            "20.627: Independent Study (Collective Architectural Design Mechanics)",
            "20.650: Research Project I",
            "20.651: Research Project II",
            "20.801: Future Cities",
            "20.802: Methods in the Study of Architecture",
            "20.803: Advanced Topics in Digital Design and Fabrication",
            "20.804: Advanced Topics in Performative Design: Urban Sustainability",
            "20.805: Conservation Theories and Approaches of Built Heritage",
            "20.806: Integrated Building Design",
            "20.807: Toward Carbon-Neutral Architecture and Urban Design",
            "20.808: Scientific Approaches to Green Design in Urban and Natural Environment",
            "20.809: Social Architecture: Theory and Practice",
            "20.900: PhD Research Pre-Candidacy",
            "20.910: PhD Research Post-Candidacy",
            "20.XXX: Urban Sketching",
            "20XXX: Thesis Preparation 2",
            "20XXX: Alternative Practice-Emergent Design Technologies",
            "20XXX: Industry Speakers Series (Structured Internship Part II)",
            "30.001: Structures & Materials",
            "30.002: Circuits & Electronics",
            "30.003: Probability & Statistics",
            "30.007: Engineering Design and Project Engineering",
            "30.101: Systems & Control",
            "30.102: Electromagnetics & Applications",
            "30.103: Fluid Mechanics",
            "30.104: Dynamics",
            "30.105: Machine Element Design",
            "30.106: Microelectronic Circuits & Devices",
            "30.107: Power Electronics",
            "30.108: Introduction to Materials Science",
            "30.109: Thermal Systems for Power & Environment",
            "30.110: Digital Systems Lab",
            "30.111: Entrepreneurship",
            "30.113: Design & Fabrication of Microelectromechanical Systems",
            "30.114: Advanced Feedback & Control",
            "30.115: Digital Signal Processing",
            "30.116: Introduction to Nanoelectronics",
            "30.117: Electric Power Systems Analysis and Design",
            "30.118: Optical Engineering and Communications",
            "30.119: Theory and Applications of Robotics",
            "30.123: Healthcare Product Design",
            "30.200: Micro and Nano Project Lab",
            "30.201: Wireless Communications",
            "30.202: Digital Integrated Circuits Design",
            "30.203: Topics in Biomedical and Healthcare Engineering",
            "30.204: Propulsion",
            "30.205: Aerodynamics",
            "30.206: Design Management",
            "30.207: Structural Mechanics and Design",
            "30.500: Applied Mathematics for Engineering",
            "30.501: Modeling Multi-Energy Systems",
            "30.502: Research Methods",
            "30.504: Computational Science & Engineering",
            "30.505: Design Science",
            "30.506: Data Structure and Algorithms",
            "30.507: Functional Materials",
            "30.508: Optimization and Control",
            "30.509: Applied Thermodynamics",
            "30.510: Quantum Computation and Quantum Information",
            "30.511: Design Management",
            "30.512: Advanced Topics in Biomedical Engineering",
            "30.513: Human Behavior, Technology and Design",
            "30.514: Optics and Photonics",
            "30.580: Research Projects I",
            "30.590: Research Seminar I",
            "30.591: Research Seminar II",
            "30.600: Special Topics (Psychology)",
            "30.601: Independent Study (Physics, Optics and Photonics)",
            "30.621: Independent Study (Robotics)",
            "30.622: Independent Study (Material Science)",
            "30.623: Independent Study (Applied Statistics)",
            "30.624: Independent Study (Materials Fabrication and Processing)",
            "30.625: Independent Study (Electronic Devices)",
            "30.626: Independent Study (Nonlinear Systems)",
            "30.627: Independent Study (Advanced Linear Systems and Control)",
            "30.628: Independent Study (Singapore Stanford Biodesign)",
            "30.629: Independent Study (Microfluidics)",
            "30.801: Industry Design Project",
            "30.900: PhD Research Pre-Candidacy",
            "30.901: Graduate Project Seminar I",
            "30.910: PhD Research Post-Candidacy",
            "31.006: Mechanical Projects Laboratory",
            "31.009: Fluid Flow and Fluid Machinery",
            "40.000: Engineering Systems Design",
            "40.001: Probability",
            "40.002: Optimization",
            "40.003: Operations Management",
            "40.004: Statistics",
            "40.005: Introduction to Stochastic Modeling",
            "40.006: Network Modeling",
            "40.007: Decision Analysis",
            "40.008: Quality & Reliability",
            "40.009: Simulation",
            "40.010: Project Management",
            "40.011: Data and Business Analytics",
            "40.012: Manufacturing and Service Operations",
            "40.013: Network Modelling and Control",
            "40.014: Engineering Systems Architecture",
            "40.015: Simulation Modeling and Analysis",
            "40.220: The Analytics Edge",
            "40.230: Sustainable Engineering",
            "40.232: Water Resources Management",
            "40.240: Investment Science",
            "40.242: Derivative Pricing and Risk Management",
            "40.244: Stochastic Calculus for Finance",
            "40.246: Computational Methods in Finance",
            "40.260: Supply Chain Management",
            "40.302: Advanced Topics in Optimization",
            "40.305: Advanced Topics in Stochastic Modeling",
            "40.316: Game Theory",
            "40.510: Linear Optimization",
            "40.520: Stochastic Modeling",
            "40.521: Probability Theory",
            "40.530: Statistics",
            "40.540: Operations Management",
            "40.541: Revenue Management",
            "40.550: Microeconomics",
            "40.570: Linear Control Systems",
            "40.590: Research Seminar",
            "40.650: Game Theory",
            "40.671: Dynamic Programming",
            "40.750: Algorithmic Game Theory",
            "40.752: Economics of Communication Networks",
            "40.811: Independent Study (Randomized Algorithms)",
            "40.821: Independent Study (Jump Processes and Financial Modelling)",
            "40.840: Special Topics (Operations Management)",
            "40.900: PhD Research Pre-Candidacy",
            "40.910: PhD Research Post-Candidacy",
            "40.911: Research Project I",
            "40.912: Research Project II",
            "41.500: Real Analysis",
            "41.520: Discrete Mathematics",
            "45.240: Trade Finance",
            "45.242: Shipping Business",
            "45.811: Special Topics (Algebraic Geometry)",
            "45.841: Special Topics (Inventory Theory)",
            "45.842: Special Topics (Logistics and Supply Chain)",
            "50.000: ISTD Mapping Subject",
            "50.001: Introduction to Information Systems & Programming",
            "50.002: Computation Structures",
            "50.003: Elements of Software Construction",
            "50.004: Introduction to Algorithms",
            "50.005: Computer System Engineering",
            "50.006: User Interface Design & Implementation",
            "50.008: Database",
            "50.009: Performance Engineering of Software Systems",
            "50.012: Networks",
            "50.017: Graphics & Visualization",
            "50.020: Security",
            "50.021: Artificial Intelligence",
            "50.033: Foundations of Game Design and Development",
            "50.034: Introduction to Probability & Statistics",
            "50.035: Computer Vision",
            "50.500: Analysis of Algorithms",
            "50.510: Computer Networks",
            "50.511: Wireless Communications and Networking",
            "50.520: Systems Security",
            "50.530: Software Engineering",
            "50.531: Secure Software Engineering",
            "50.570: Machine Learning",
            "50.571: Digital Signal Processing",
            "50.572: Graphics & Visualization",
            "50.573: Database Systems",
            "50.574: Foundations of Cyber Security",
            "50.575: Security Tools Laboratory 1",
            "50.576: Security Tools Laboratory 2",
            "50.577: Secure Cyber Physical Systems",
            "50.580: Project",
            "50.590: Research Seminar",
            "50.900: PhD Research Pre-Candidacy",
            "50.910: PhD Research Post-Candidacy",
            "50.920: MSSD Master Project",
            "99.580: General Track Research Project",
            "99.590: General Track Research Seminar Series",
            "99.900: PhD Research Pre-Candidacy"
    };

}
