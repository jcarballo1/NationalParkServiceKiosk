<%-- 
    Document    : CEResultPage
    Created on  : May 26, 2019, 6:25:58 PM
    Author      : Jennifer Carballo
    Description : JSP in response to Current Events search query, prints out results of search; 
                  passes input to Post-Servlet; passes input to Pre-Servlet for next search request
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.mypackage.nationalpark.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import ="org.mypackage.nationalpark.CESearchRequest" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Current Events</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link rel="stylesheet" href="assets/css/main.css" />
    </head>

    <body>
        <!-- Header -->
    <header id="header">
        <a class="logo" href="index.html">National Park Service Kiosk</a>
        <nav>
            <a href="#menu">Menu</a>
        </nav>
    </header>

    <!-- Nav -->
    <nav id="menu">
        <ul class="links">
            <li><a href="index.html">Home</a></li>
            <li><a href="GeneralSearch.jsp">General Park Information</a></li>
            <li><a href="VisitorCenter.jsp">Visitor Centers</a></li>
            <li><a href="CurrentEvents.jsp">Current Events</a></li>
            <li><a href="Education.jsp">Education</a></li>
            <li><a href="Map.jsp">Map</a></li>
            <li><a href="Gallery.jsp">Destination Gallery</a></li>
        </ul>
    </nav>

    <!-- Heading w/ Custom Styling -->
    <div id="heading" style="background-image: linear-gradient(rgba(17, 17, 17, 0.25), rgba(17, 17, 17, 0.25)), url(images/Event.jpg); background-position: bottom"> 
        <h1>Current Events</h1>
    </div>

    <!-- Main -->
    <section id="main" class="wrapper">
        <div class="inner">
            <header class="special">
                <h2>Keep Up With Alerts, News Releases & More.</h2>
                <p>Select your destination <b>-OR-</b> state of choice below.<br>If searching by keyword, specify a type or state for accurate results.</p>
            </header>

            <!-- Back to Top Button Functionality -->
            <script>
                // When the user scrolls down 20px from the top of the document, show the button
                window.onscroll = function () {
                    scrollFunction()
                };

                function scrollFunction() {
                    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                        document.getElementById("myBtn").style.display = "block";
                    } else {
                        document.getElementById("myBtn").style.display = "none";
                    }
                }

                // When the user clicks on the button, scroll to the top of the document
                function topFunction() {
                    document.body.scrollTop = 0;
                    document.documentElement.scrollTop = 0;
                }
            </script>

            <button onclick="topFunction()" id="myBtn" title="Go to top">Back to Top</button>

            <div class="content">
                <form name="General Search" action="CEServletPre">
                    <!-- Keyword Search Bar-->
                    <div style="margin-bottom: 30px">
                        <div class="row">
                            <div class="col-12">
                                <input type="text" name="keyword" placeholder="Keyword Search" />
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <!-- Destination Drop Down -->
                        <div class="col-5">
                            <select name="destination" id="destination">
                                <option value="">All Destinations</option>

                                <optgroup label="A">
                                    <option value="abli">Abraham Lincoln Birthplace National Historical Park</option>
                                    <option value="acad">Acadia National Park</option>
                                    <option value="adam">Adams National Historical Park</option>
                                    <option value="afam">African American Civil War Memorial </option>
                                    <option value="afbg">African Burial Ground National Monument</option>
                                    <option value="agfo">Agate Fossil Beds National Monument</option>
                                    <option value="alka">Ala Kahakai National Historic Trail</option>
                                    <option value="alag">Alagnak Wild River</option>
                                    <option value="anch">Alaska Public Lands </option>
                                    <option value="alca">Alcatraz Island </option>
                                    <option value="aleu">Aleutian Islands World War II National Historic Area</option>
                                    <option value="alfl">Alibates Flint Quarries National Monument</option>
                                    <option value="alpo">Allegheny Portage Railroad National Historic Site</option>
                                    <option value="amme">American Memorial Park</option>
                                    <option value="amis">Amistad National Recreation Area</option>
                                    <option value="anac">Anacostia Park</option>
                                    <option value="ande">Andersonville National Historic Site</option>
                                    <option value="anjo">Andrew Johnson National Historic Site</option>
                                    <option value="ania">Aniakchak National Monument & Preserve</option>
                                    <option value="anti">Antietam National Battlefield</option>
                                    <option value="apis">Apostle Islands National Lakeshore</option>
                                    <option value="appa">Appalachian National Scenic Trail</option>
                                    <option value="apco">Appomattox Court House National Historical Park</option>
                                    <option value="armo">Arabia Mountain National Heritage Area</option>
                                    <option value="arch">Arches National Park</option>
                                    <option value="arpo">Arkansas Post National Memorial</option>
                                    <option value="arho">Arlington House, The Robert  E. Lee Memorial </option>
                                    <option value="asis">Assateague Island National Seashore</option>
                                    <option value="attr">Atchafalaya National Heritage Area</option>
                                    <option value="auca">Augusta Canal National Heritage Area</option>
                                    <option value="azru">Aztec Ruins National Monument</option>
                                </optgroup>
                                <optgroup label="B">
                                    <option value="badl">Badlands National Park</option>
                                    <option value="balt">Baltimore National Heritage Area</option>
                                    <option value="bawa">Baltimore-Washington Parkway</option>
                                    <option value="band">Bandelier National Monument</option>
                                    <option value="bepa">Belmont-Paul Women's Equality National Monument</option>
                                    <option value="beol">Bent's Old Fort National Historic Site</option>
                                    <option value="bela">Bering Land Bridge National Preserve</option>
                                    <option value="bibe">Big Bend National Park</option>
                                    <option value="bicy">Big Cypress National Preserve</option>
                                    <option value="biho">Big Hole National Battlefield</option>
                                    <option value="biso">Big South Fork National River & Recreation Area</option>
                                    <option value="bith">Big Thicket National Preserve</option>
                                    <option value="bica">Bighorn Canyon National Recreation Area</option>
                                    <option value="bicr">Birmingham Civil Rights National Monument</option>
                                    <option value="bisc">Biscayne National Park</option>
                                    <option value="blca">Black Canyon Of The Gunnison National Park</option>
                                    <option value="blrv">Blackstone River Valley National Historical Park</option>
                                    <option value="blrn">Blue Ridge National Heritage Area</option>
                                    <option value="blri">Blue Ridge Parkway</option>
                                    <option value="blue">Bluestone National Scenic River</option>
                                    <option value="bowa">Booker T Washington National Monument</option>
                                    <option value="boaf">Boston African American National Historic Site</option>
                                    <option value="boha">Boston Harbor Islands National Recreation Area</option>
                                    <option value="bost">Boston National Historical Park</option>
                                    <option value="brcr">Brices Cross Roads National Battlefield Site</option>
                                    <option value="brvb">Brown v. Board of Education National Historic Site</option>
                                    <option value="brca">Bryce Canyon National Park</option>
                                    <option value="buis">Buck Island Reef National Monument</option>
                                    <option value="buff">Buffalo National River</option>
                                </optgroup>
                                <optgroup label="C">
                                    <option value="cabr">Cabrillo National Monument</option>
                                    <option value="cali">California National Historic Trail</option>
                                    <option value="cane">Camp Nelson National Monument</option>
                                    <option value="cana">Canaveral National Seashore</option>
                                    <option value="cari">Cane River Creole National Historical Park</option>
                                    <option value="crha">Cane River National Heritage Area</option>
                                    <option value="cach">Canyon de Chelly National Monument</option>
                                    <option value="cany">Canyonlands National Park</option>
                                    <option value="caco">Cape Cod National Seashore</option>
                                    <option value="caha">Cape Hatteras National Seashore</option>
                                    <option value="came">Cape Henry Memorial Part of Colonial National Historical Park</option>
                                    <option value="cakr">Cape Krusenstern National Monument</option>
                                    <option value="calo">Cape Lookout National Seashore</option>
                                    <option value="cahi">Capitol Hill Parks </option>
                                    <option value="care">Capitol Reef National Park</option>
                                    <option value="cajo">Captain John Smith Chesapeake National Historic Trail</option>
                                    <option value="cavo">Capulin Volcano National Monument</option>
                                    <option value="carl">Carl Sandburg Home National Historic Site</option>
                                    <option value="cave">Carlsbad Caverns National Park</option>
                                    <option value="cawo">Carter G. Woodson Home National Historic Site</option>
                                    <option value="cagr">Casa Grande Ruins National Monument</option>
                                    <option value="casa">Castillo de San Marcos National Monument</option>
                                    <option value="cacl">Castle Clinton National Monument</option>
                                    <option value="camo">Castle Mountains National Monument</option>
                                    <option value="cato">Catoctin Mountain Park</option>
                                    <option value="cebr">Cedar Breaks National Monument</option>
                                    <option value="cebe">Cedar Creek & Belle Grove National Historical Park</option>
                                    <option value="chcu">Chaco Culture National Historical Park</option>
                                    <option value="cham">Chamizal National Memorial</option>
                                    <option value="chva">Champlain Valley National Heritage Partnership</option>
                                    <option value="chis">Channel Islands National Park</option>
                                    <option value="chpi">Charles Pinckney National Historic Site</option>
                                    <option value="chyo">Charles Young Buffalo Soldiers National Monument</option>
                                    <option value="chat">Chattahoochee River National Recreation Area</option>
                                    <option value="choh">Chesapeake & Ohio Canal National Historical Park</option>
                                    <option value="cbpo">Chesapeake Bay </option>
                                    <option value="chch">Chickamauga & Chattanooga National Military Park</option>
                                    <option value="chic">Chickasaw National Recreation Area</option>
                                    <option value="chir">Chiricahua National Monument</option>
                                    <option value="chri">Christiansted National Historic Site</option>
                                    <option value="ciro">City Of Rocks National Reserve</option>
                                    <option value="cwdw">Civil War Defenses of Washington </option>
                                    <option value="clba">Clara Barton National Historic Site</option>
                                    <option value="coal">Coal National Heritage Area</option>
                                    <option value="colo">Colonial National Historical Park</option>
                                    <option value="colm">Colorado National Monument</option>
                                    <option value="colt">Coltsville National Historical Park</option>
                                    <option value="cong">Congaree National Park</option>
                                    <option value="coga">Constitution Gardens </option>
                                    <option value="coro">Coronado National Memorial</option>
                                    <option value="cowp">Cowpens National Battlefield</option>
                                    <option value="crla">Crater Lake National Park</option>
                                    <option value="crmo">Craters Of The Moon National Monument & Preserve</option>
                                    <option value="xrds">Crossroads of the American Revolution National Heritage Area</option>
                                    <option value="cuga">Cumberland Gap National Historical Park</option>
                                    <option value="cuis">Cumberland Island National Seashore</option>
                                    <option value="cure">Curecanti National Recreation Area</option>
                                    <option value="cuva">Cuyahoga Valley National Park</option>
                                    <option value="cech">César E. Chávez National Monument</option>
                                </optgroup>
                                <optgroup label="D">
                                    <option value="dabe">David Berger National Memorial</option>
                                    <option value="daav">Dayton Aviation Heritage National Historical Park</option>
                                    <option value="deso">De Soto National Memorial</option>
                                    <option value="deva">Death Valley National Park</option>
                                    <option value="dele">Delaware & Lehigh National Heritage Corridor</option>
                                    <option value="dewa">Delaware Water Gap National Recreation Area</option>
                                    <option value="dena">Denali National Park & Preserve</option>
                                    <option value="depo">Devils Postpile National Monument</option>
                                    <option value="deto">Devils Tower National Monument</option>
                                    <option value="dino">Dinosaur National Monument</option>
                                    <option value="drto">Dry Tortugas National Park</option>
                                </optgroup>
                                <optgroup label="E">
                                    <option value="ebla">Ebey's Landing National Historical Reserve</option>
                                    <option value="edal">Edgar Allan Poe National Historic Site</option>
                                    <option value="efmo">Effigy Mounds National Monument</option>
                                    <option value="eise">Eisenhower National Historic Site</option>
                                    <option value="elte">El Camino Real de los Tejas National Historic Trail</option>
                                    <option value="elca">El Camino Real de Tierra Adentro National Historic Trail</option>
                                    <option value="elma">El Malpais National Monument</option>
                                    <option value="elmo">El Morro National Monument</option>
                                    <option value="elro">Eleanor Roosevelt National Historic Site</option>
                                    <option value="elis">Ellis Island Part of Statue of Liberty National Monument</option>
                                    <option value="erie">Erie Canalway National Heritage Corridor</option>
                                    <option value="esse">Essex National Heritage Area</option>
                                    <option value="euon">Eugene O'Neill National Historic Site</option>
                                    <option value="ever">Everglades National Park</option>
                                </optgroup>
                                <optgroup label="F">
                                    <option value="fati">Fallen Timbers Battlefield and Fort Miamis National Historic Site</option>
                                    <option value="feha">Federal Hall National Memorial</option>
                                    <option value="fiis">Fire Island National Seashore</option>
                                    <option value="fila">First Ladies National Historic Site</option>
                                    <option value="frst">First State National Historical Park</option>
                                    <option value="flni">Flight 93 National Memorial</option>
                                    <option value="flfo">Florissant Fossil Beds National Monument</option>
                                    <option value="foth">Ford's Theatre </option>
                                    <option value="fobo">Fort Bowie National Historic Site</option>
                                    <option value="foca">Fort Caroline National Memorial</option>
                                    <option value="foda">Fort Davis National Historic Site</option>
                                    <option value="fodo">Fort Donelson National Battlefield</option>
                                    <option value="fodu">Fort Dupont Park </option>
                                    <option value="fofo">Fort Foote Park</option>
                                    <option value="fofr">Fort Frederica National Monument</option>
                                    <option value="fola">Fort Laramie National Historic Site</option>
                                    <option value="fols">Fort Larned National Historic Site</option>
                                    <option value="foma">Fort Matanzas National Monument</option>
                                    <option value="fomc">Fort McHenry National Monument and Historic Shrine</option>
                                    <option value="fomr">Fort Monroe National Monument</option>
                                    <option value="fone">Fort Necessity National Battlefield</option>
                                    <option value="fopo">Fort Point National Historic Site</option>
                                    <option value="fopu">Fort Pulaski National Monument</option>
                                    <option value="fora">Fort Raleigh National Historic Site</option>
                                    <option value="fosc">Fort Scott National Historic Site</option>
                                    <option value="fosm">Fort Smith National Historic Site</option>
                                    <option value="fost">Fort Stanwix National Monument</option>
                                    <option value="fosu">Fort Sumter and Fort Moultrie National Historical Park</option>
                                    <option value="foun">Fort Union National Monument</option>
                                    <option value="fous">Fort Union Trading Post National Historic Site</option>
                                    <option value="fova">Fort Vancouver National Historic Site</option>
                                    <option value="fowa">Fort Washington Park</option>
                                    <option value="fobu">Fossil Butte National Monument</option>
                                    <option value="frde">Franklin Delano Roosevelt Memorial </option>
                                    <option value="frdo">Frederick Douglass National Historic Site</option>
                                    <option value="frla">Frederick Law Olmsted National Historic Site</option>
                                    <option value="frsp">Fredericksburg & Spotsylvania National Military Park</option>
                                    <option value="frri">Freedom Riders National Monument</option>
                                    <option value="frwa">Freedom's Way National Heritage Area</option>
                                    <option value="frhi">Friendship Hill National Historic Site</option>
                                </optgroup>
                                <optgroup label="G">
                                    <option value="gaar">Gates Of The Arctic National Park & Preserve</option>
                                    <option value="jeff">Gateway Arch National Park</option>
                                    <option value="gate">Gateway National Recreation Area</option>
                                    <option value="gari">Gauley River National Recreation Area</option>
                                    <option value="gegr">General Grant National Memorial</option>
                                    <option value="gero">George Rogers Clark National Historical Park</option>
                                    <option value="gewa">George Washington Birthplace National Monument</option>
                                    <option value="gwca">George Washington Carver National Monument</option>
                                    <option value="gwmp">George Washington Memorial Parkway</option>
                                    <option value="gett">Gettysburg National Military Park</option>
                                    <option value="gicl">Gila Cliff Dwellings National Monument</option>
                                    <option value="glba">Glacier Bay National Park & Preserve</option>
                                    <option value="glac">Glacier National Park</option>
                                    <option value="glca">Glen Canyon National Recreation Area</option>
                                    <option value="glec">Glen Echo Park</option>
                                    <option value="glde">Gloria Dei Church National Historic Site</option>
                                    <option value="goga">Golden Gate National Recreation Area</option>
                                    <option value="gosp">Golden Spike National Historical Park</option>
                                    <option value="gois">Governors Island National Monument</option>
                                    <option value="grca">Grand Canyon National Park</option>
                                    <option value="grpo">Grand Portage National Monument</option>
                                    <option value="grte">Grand Teton National Park</option>
                                    <option value="grko">Grant-Kohrs Ranch National Historic Site</option>
                                    <option value="grba">Great Basin National Park</option>
                                    <option value="greg">Great Egg Harbor River </option>
                                    <option value="grfa">Great Falls Park</option>
                                    <option value="grsa">Great Sand Dunes National Park & Preserve</option>
                                    <option value="grsm">Great Smoky Mountains National Park</option>
                                    <option value="grsp">Green Springs </option>
                                    <option value="gree">Greenbelt Park</option>
                                    <option value="gumo">Guadalupe Mountains National Park</option>
                                    <option value="guco">Guilford Courthouse National Military Park</option>
                                    <option value="guis">Gulf Islands National Seashore</option>
                                    <option value="guge">Gullah/Geechee Cultural Heritage Corridor</option>
                                </optgroup>
                                <optgroup label="H">
                                    <option value="hafo">Hagerman Fossil Beds National Monument</option>
                                    <option value="hale">Haleakalā National Park</option>
                                    <option value="hagr">Hamilton Grange National Memorial</option>
                                    <option value="hamp">Hampton National Historic Site</option>
                                    <option value="haha">Harmony Hall </option>
                                    <option value="hafe">Harpers Ferry National Historical Park</option>
                                    <option value="hart">Harriet Tubman National Historical Park</option>
                                    <option value="hatu">Harriet Tubman Underground Railroad National Historical Park</option>
                                    <option value="hstr">Harry S Truman National Historic Site</option>
                                    <option value="havo">Hawai'i Volcanoes National Park</option>
                                    <option value="heho">Herbert Hoover National Historic Site</option>
                                    <option value="jame">Historic Jamestowne Part of Colonial National Historical Park</option>
                                    <option value="hofr">Home Of Franklin D Roosevelt National Historic Site</option>
                                    <option value="home">Homestead National Monument of America</option>
                                    <option value="hono">Honouliuli National Historic Site</option>
                                    <option value="hocu">Hopewell Culture National Historical Park</option>
                                    <option value="hofu">Hopewell Furnace National Historic Site</option>
                                    <option value="hobe">Horseshoe Bend National Military Park</option>
                                    <option value="hosp">Hot Springs National Park</option>
                                    <option value="hove">Hovenweep National Monument</option>
                                    <option value="hutr">Hubbell Trading Post National Historic Site</option>
                                    <option value="hurv">Hudson River Valley National Heritage Area</option>
                                </optgroup>
                                <optgroup label="I">
                                    <option value="inup">Iñupiat Heritage Center </option>
                                    <option value="iafl">Ice Age Floods National Geologic Trail</option>
                                    <option value="iatr">Ice Age National Scenic Trail</option>
                                    <option value="inde">Independence National Historical Park</option>
                                    <option value="indu">Indiana Dunes National Park</option>
                                    <option value="isro">Isle Royale National Park</option>
                                </optgroup>
                                <optgroup label="J">
                                    <option value="jaga">James A Garfield National Historic Site</option>
                                    <option value="jela">Jean Lafitte National Historical Park and Preserve</option>
                                    <option value="jeca">Jewel Cave National Monument</option>
                                    <option value="jica">Jimmy Carter National Historic Site</option>
                                    <option value="joda">John Day Fossil Beds National Monument</option>
                                    <option value="jofi">John Fitzgerald Kennedy National Historic Site</option>
                                    <option value="blac">John H. Chafee Blackstone River Valley National Heritage Corridor</option>
                                    <option value="jomu">John Muir National Historic Site</option>
                                    <option value="jofl">Johnstown Flood National Memorial</option>
                                    <option value="jotr">Joshua Tree National Park</option>
                                    <option value="jthg">Journey Through Hallowed Ground National Heritage Area</option>
                                    <option value="juba">Juan Bautista de Anza National Historic Trail</option>
                                </optgroup>
                                <optgroup label="K">
                                    <option value="kala">Kalaupapa National Historical Park</option>
                                    <option value="kaho">Kaloko-Honokōhau National Historical Park</option>
                                    <option value="kaww">Katahdin Woods and Waters National Monument</option>
                                    <option value="katm">Katmai National Park & Preserve</option>
                                    <option value="kefj">Kenai Fjords National Park</option>
                                    <option value="keaq">Kenilworth Park & Aquatic Gardens </option>
                                    <option value="kemo">Kennesaw Mountain National Battlefield Park</option>
                                    <option value="kewe">Keweenaw National Historical Park</option>
                                    <option value="kimo">Kings Mountain National Military Park</option>
                                    <option value="klse">Klondike Gold Rush - Seattle Unit National Historical Park</option>
                                    <option value="klgo">Klondike Gold Rush National Historical Park</option>
                                    <option value="knri">Knife River Indian Villages National Historic Site</option>
                                    <option value="kova">Kobuk Valley National Park</option>
                                    <option value="kowa">Korean War Veterans Memorial </option>
                                </optgroup>
                                <optgroup label="L">
                                    <option value="lacl">Lake Clark National Park & Preserve</option>
                                    <option value="lake">Lake Mead National Recreation Area</option>
                                    <option value="lamr">Lake Meredith National Recreation Area</option>
                                    <option value="laro">Lake Roosevelt National Recreation Area</option>
                                    <option value="lavo">Lassen Volcanic National Park</option>
                                    <option value="labe">Lava Beds National Monument</option>
                                    <option value="lyba">LBJ Memorial Grove on the Potomac </option>
                                    <option value="lecl">Lewis & Clark National Historic Trail</option>
                                    <option value="lewi">Lewis and Clark National Historical Park</option>
                                    <option value="libo">Lincoln Boyhood National Memorial</option>
                                    <option value="liho">Lincoln Home National Historic Site</option>
                                    <option value="linc">Lincoln Memorial </option>
                                    <option value="libi">Little Bighorn Battlefield National Monument</option>
                                    <option value="liri">Little River Canyon National Preserve</option>
                                    <option value="chsc">Little Rock Central High School National Historic Site</option>
                                    <option value="long">Longfellow House Washington's Headquarters National Historic Site</option>
                                    <option value="lowe">Lowell National Historical Park</option>
                                    <option value="lode">Lower Delaware National Wild and Scenic River</option>
                                    <option value="loea">Lower East Side Tenement Museum National Historic Site</option>
                                    <option value="lyjo">Lyndon B Johnson National Historical Park</option>
                                </optgroup>
                                <optgroup label="M">
                                    <option value="mawa">Maggie L Walker National Historic Site</option>
                                    <option value="maac">Maine Acadian Culture </option>
                                    <option value="maca">Mammoth Cave National Park</option>
                                    <option value="mana">Manassas National Battlefield Park</option>
                                    <option value="mapr">Manhattan Project National Historical Park</option>
                                    <option value="manz">Manzanar National Historic Site</option>
                                    <option value="mabi">Marsh - Billings - Rockefeller National Historical Park</option>
                                    <option value="mlkm">Martin Luther King, Jr. Memorial </option>
                                    <option value="malu">Martin Luther King, Jr. National Historical Park</option>
                                    <option value="mava">Martin Van Buren National Historic Site</option>
                                    <option value="mamc">Mary McLeod Bethune Council House National Historic Site
                                    </option>
                                    <option value="meve">Mesa Verde National Park</option>
                                    <option value="miin">Minidoka National Historic Site</option>
                                    <option value="mima">Minute Man National Historical Park</option>
                                    <option value="mimi">Minuteman Missile National Historic Site</option>
                                    <option value="mide">Mississippi Delta National Heritage Area</option>
                                    <option value="migu">Mississippi Gulf National Heritage Area</option>
                                    <option value="mihi">Mississippi Hills National Heritage Area</option>
                                    <option value="miss">Mississippi National River and Recreation Area</option>
                                    <option value="mnrr">Missouri National Recreational River</option>
                                    <option value="moja">Mojave National Preserve</option>
                                    <option value="mono">Monocacy National Battlefield</option>
                                    <option value="moca">Montezuma Castle National Monument</option>
                                    <option value="mocr">Moores Creek National Battlefield</option>
                                    <option value="mopi">Mormon Pioneer National Historic Trail</option>
                                    <option value="morr">Morristown National Historical Park</option>
                                    <option value="auto">Motor Cities National Heritage Area</option>
                                    <option value="mora">Mount Rainier National Park</option>
                                    <option value="moru">Mount Rushmore National Memorial</option>
                                    <option value="muwo">Muir Woods National Monument</option>
                                    <option value="mush">Muscle Shoals National Heritage Area</option>
                                </optgroup>
                                <optgroup label="N">
                                    <option value="natc">Natchez National Historical Park</option>
                                    <option value="natt">Natchez Trace National Scenic Trail</option>
                                    <option value="natr">Natchez Trace Parkway</option>
                                    <option value="avia">National Aviation Heritage Area</option>
                                    <option value="nace">National Capital Parks-East </option>
                                    <option value="nama">National Mall and Memorial Parks </option>
                                    <option value="npsa">National Park of American Samoa </option>
                                    <option value="npnh">National Parks of New York Harbor </option>
                                    <option value="nabr">Natural Bridges National Monument</option>
                                    <option value="nava">Navajo National Monument</option>
                                    <option value="nebe">New Bedford Whaling National Historical Park</option>
                                    <option value="neen">New England National Scenic Trail</option>
                                    <option value="pine">New Jersey Pinelands National Reserve</option>
                                    <option value="jazz">New Orleans Jazz National Historical Park</option>
                                    <option value="neri">New River Gorge National River</option>
                                    <option value="nepe">Nez Perce National Historical Park</option>
                                    <option value="nifa">Niagara Falls National Heritage Area</option>
                                    <option value="nico">Nicodemus National Historic Site</option>
                                    <option value="nisi">Ninety Six National Historic Site</option>
                                    <option value="niob">Niobrara National Scenic River</option>
                                    <option value="noat">Noatak National Preserve</option>
                                    <option value="noca">North Cascades National Park</option>
                                    <option value="noco">North Country National Scenic Trail</option>
                                </optgroup>
                                <optgroup label="O">
                                    <option value="obed">Obed Wild & Scenic River</option>
                                    <option value="ocmu">Ocmulgee Mounds National Historical Park</option>
                                    <option value="oire">Oil Region National Heritage Area</option>
                                    <option value="okci">Oklahoma City National Memorial</option>
                                    <option value="olsp">Old Spanish National Historic Trail</option>
                                    <option value="olym">Olympic National Park</option>
                                    <option value="orca">Oregon Caves National Monument & Preserve</option>
                                    <option value="oreg">Oregon National Historic Trail</option>
                                    <option value="orpi">Organ Pipe Cactus National Monument</option>
                                    <option value="ovvi">Overmountain Victory National Historic Trail</option>
                                    <option value="oxhi">Oxon Cove  Park & Oxon Hill Farm </option>
                                    <option value="ozar">Ozark National Scenic Riverways</option>
                                </optgroup>
                                <optgroup label="P">
                                    <option value="pais">Padre Island National Seashore</option>
                                    <option value="paal">Palo Alto Battlefield National Historical Park</option>
                                    <option value="para">Parashant National Monument</option>
                                    <option value="pagr">Paterson Great Falls National Historical Park</option>
                                    <option value="peri">Pea Ridge National Military Park</option>
                                    <option value="valr">Pearl Harbor National Memorial</option>
                                    <option value="peco">Pecos National Historical Park</option>
                                    <option value="paav">Pennsylvania Avenue </option>
                                    <option value="pevi">Perry's Victory & International Peace Memorial</option>
                                    <option value="pete">Petersburg National Battlefield</option>
                                    <option value="pefo">Petrified Forest National Park</option>
                                    <option value="petr">Petroglyph National Monument</option>
                                    <option value="piro">Pictured Rocks National Lakeshore</option>
                                    <option value="pinn">Pinnacles National Park</option>
                                    <option value="pisp">Pipe Spring National Monument</option>
                                    <option value="pipe">Pipestone National Monument</option>
                                    <option value="pisc">Piscataway Park</option>
                                    <option value="pore">Point Reyes National Seashore</option>
                                    <option value="poex">Pony Express National Historic Trail</option>
                                    <option value="poch">Port Chicago Naval Magazine National Memorial</option>
                                    <option value="pohe">Potomac Heritage National Scenic Trail</option>
                                    <option value="popo">Poverty Point National Monument</option>
                                    <option value="wicl">President William Jefferson Clinton Birthplace Home National Historic Site</option>
                                    <option value="whho">President's Park (White House) </option>
                                    <option value="prsf">Presidio of San Francisco </option>
                                    <option value="prwi">Prince William Forest Park</option>
                                    <option value="puho">Pu`uhonua O Hōnaunau National Historical Park</option>
                                    <option value="puhe">Pu`ukoholā Heiau National Historic Site</option>
                                    <option value="pull">Pullman National Monument</option>
                                </optgroup>
                                <optgroup label="R">
                                    <option value="rabr">Rainbow Bridge National Monument</option>
                                    <option value="reer">Reconstruction Era National Historical Park</option>
                                    <option value="redw">Redwood National and State Parks</option>
                                    <option value="rich">Richmond National Battlefield Park</option>
                                    <option value="rigr">Rio Grande Wild & Scenic River</option>
                                    <option value="rira">River Raisin National Battlefield Park</option>
                                    <option value="rist">Rivers Of Steel National Heritage Area</option>
                                    <option value="rocr">Rock Creek Park</option>
                                    <option value="romo">Rocky Mountain National Park</option>
                                    <option value="rowi">Roger Williams National Memorial</option>
                                    <option value="roca">Roosevelt Campobello International Park</option>
                                    <option value="rori">Rosie the Riveter WWII Home Front National Historical Park</option>
                                    <option value="ruca">Russell Cave National Monument</option>
                                </optgroup>
                                <optgroup label="S">
                                    <option value="sahi">Sagamore Hill National Historic Site</option>
                                    <option value="sagu">Saguaro National Park</option>
                                    <option value="sacr">Saint Croix Island International Historic Site</option>
                                    <option value="sacn">Saint Croix National Scenic Riverway</option>
                                    <option value="sapa">Saint Paul's Church National Historic Site</option>
                                    <option value="saga">Saint-Gaudens National Historical Park</option>
                                    <option value="sama">Salem Maritime National Historic Site</option>
                                    <option value="sapu">Salinas Pueblo Missions National Monument</option>
                                    <option value="sari">Salt River Bay National Historical Park and Ecological Preserve</option>
                                    <option value="saan">San Antonio Missions National Historical Park</option>
                                    <option value="safr">San Francisco Maritime National Historical Park</option>
                                    <option value="sajh">San Juan Island National Historical Park</option>
                                    <option value="saju">San Juan National Historic Site</option>
                                    <option value="sand">Sand Creek Massacre National Historic Site</option>
                                    <option value="safe">Santa Fe National Historic Trail</option>
                                    <option value="samo">Santa Monica Mountains National Recreation Area</option>
                                    <option value="sara">Saratoga National Historical Park</option>
                                    <option value="sair">Saugus Iron Works National Historic Site</option>
                                    <option value="scrv">Schuylkill River Valley National Heritage Area</option>
                                    <option value="scbl">Scotts Bluff National Monument</option>
                                    <option value="semo">Selma To Montgomery National Historic Trail</option>
                                    <option value="seki">Sequoia & Kings Canyon National Parks</option>
                                    <option value="shen">Shenandoah National Park</option>
                                    <option value="shvb">Shenandoah Valley Battlefields National Historic District</option>
                                    <option value="shil">Shiloh National Military Park</option>
                                    <option value="sitk">Sitka National Historical Park</option>
                                    <option value="slbe">Sleeping Bear Dunes National Lakeshore</option>
                                    <option value="soca">South Carolina National Heritage Corridor</option>
                                    <option value="spar">Springfield Armory National Historic Site</option>
                                    <option value="stsp">Star-Spangled Banner National Historic Trail</option>
                                    <option value="stli">Statue Of Liberty National Monument</option>
                                    <option value="stea">Steamtown National Historic Site</option>
                                    <option value="stri">Stones River National Battlefield</option>
                                    <option value="ston">Stonewall National Monument</option>
                                    <option value="sucr">Sunset Crater Volcano National Monument</option>
                                </optgroup>
                                <optgroup label="T">
                                    <option value="tapr">Tallgrass Prairie National Preserve</option>
                                    <option value="tecw">Tennessee Civil War National Heritage Area</option>
                                    <option value="thko">Thaddeus Kosciuszko National Memorial</option>
                                    <option value="qush">The Last Green Valley National Heritage Corridor</option>
                                    <option value="thrb">Theodore Roosevelt Birthplace National Historic Site</option>
                                    <option value="thri">Theodore Roosevelt Inaugural National Historic Site</option>
                                    <option value="this">Theodore Roosevelt Island </option>
                                    <option value="thro">Theodore Roosevelt National Park</option>
                                    <option value="thco">Thomas Cole National Historic Site</option>
                                    <option value="edis">Thomas Edison National Historical Park</option>
                                    <option value="thje">Thomas Jefferson Memorial </option>
                                    <option value="thst">Thomas Stone National Historic Site</option>
                                    <option value="tica">Timpanogos Cave National Monument</option>
                                    <option value="timu">Timucuan Ecological & Historic Preserve</option>
                                    <option value="tont">Tonto National Monument</option>
                                    <option value="tosy">Touro Synagogue National Historic Site</option>
                                    <option value="trte">Trail Of Tears National Historic Trail</option>
                                    <option value="tule">Tule Lake National Monument</option>
                                    <option value="tusk">Tule Springs Fossil Beds National Monument</option>
                                    <option value="tuma">Tumacácori National Historical Park</option>
                                    <option value="tupe">Tupelo National Battlefield</option>
                                    <option value="tuai">Tuskegee Airmen National Historic Site</option>
                                    <option value="tuin">Tuskegee Institute National Historic Site</option>
                                    <option value="tuzi">Tuzigoot National Monument</option>
                                </optgroup>
                                <optgroup label="U">
                                    <option value="ulsg">Ulysses S Grant National Historic Site</option>
                                    <option value="upde">Upper Delaware Scenic & Recreational River</option>
                                    <option value="uphv">Upper Housatonic Valley National Heritage Area</option>
                                </optgroup>
                                <optgroup label="V">
                                    <option value="vall">Valles Caldera National Preserve</option>
                                    <option value="vafo">Valley Forge National Historical Park</option>
                                    <option value="vama">Vanderbilt Mansion National Historic Site</option>
                                    <option value="vick">Vicksburg National Military Park</option>
                                    <option value="vive">Vietnam Veterans Memorial</option>
                                    <option value="vicr">Virgin Islands Coral Reef National Monument</option>
                                    <option value="viis">Virgin Islands National Park</option>
                                    <option value="voya">Voyageurs National Park</option>
                                </optgroup>
                                <optgroup label="W">
                                    <option value="waco">Waco Mammoth National Monument</option>
                                    <option value="waca">Walnut Canyon National Monument</option>
                                    <option value="wapa">War In The Pacific National Historical Park</option>
                                    <option value="wamo">Washington Monument </option>
                                    <option value="waro">Washington-Rochambeau National Historic Trail</option>
                                    <option value="waba">Washita Battlefield National Historic Site</option>
                                    <option value="wefa">Weir Farm National Historic Site</option>
                                    <option value="whee">Wheeling National Heritage Area</option>
                                    <option value="whis">Whiskeytown National Recreation Area</option>
                                    <option value="whsa">White Sands National Monument</option>
                                    <option value="whmi">Whitman Mission National Historic Site</option>
                                    <option value="wiho">William Howard Taft National Historic Site</option>
                                    <option value="wicr">Wilson's Creek National Battlefield</option>
                                    <option value="wica">Wind Cave National Park</option>
                                    <option value="wing">Wing Luke Museum Affiliated Area</option>
                                    <option value="wotr">Wolf Trap National Park for the Performing Arts </option>
                                    <option value="wori">Women's Rights National Historical Park</option>
                                    <option value="wwii">World War II Memorial </option>
                                    <option value="wrst">Wrangell - St Elias National Park & Preserve</option>
                                    <option value="wrbr">Wright Brothers National Memorial</option>
                                    <option value="wupa">Wupatki National Monument</option>
                                </optgroup>
                                <optgroup label="Y">
                                    <option value="yell">Yellowstone National Park</option>
                                    <option value="york">Yorktown Battlefield Part of Colonial National Historical Park</option>
                                    <option value="yose">Yosemite National Park</option>
                                    <option value="yuho">Yucca House National Monument</option>
                                    <option value="yuch">Yukon - Charley Rivers National Preserve</option>
                                </optgroup>
                                <optgroup label="Z">
                                    <option value="zion">Zion National Park</option>
                                </optgroup>
                            </select>
                        </div>
                        
                        <!-- State Drop Down -->
                        <div class="col-3">
                            <select name="state" id="state">
                                <option value="">All States</option>

                                <option value="al">Alabama</option>
                                <option value="ak">Alaska</option>
                                <option value="as">American Samoa</option>
                                <option value="az">Arizona</option>
                                <option value="ar">Arkansas</option>

                                <option value="ca">California</option>
                                <option value="co">Colorado</option>
                                <option value="ct">Connecticut</option>

                                <option value="de">Delaware</option>
                                <option value="dc">District of Columbia</option>

                                <option value="fl">Florida</option>

                                <option value="ga">Georgia</option>
                                <option value="gu">Guam</option>

                                <option value="hi">Hawaii</option>

                                <option value="id">Idaho</option>
                                <option value="il">Illinois</option>
                                <option value="in">Indiana</option>
                                <option value="ia">Iowa</option>

                                <option value="ks">Kansas</option>
                                <option value="ky">Kentucky</option>

                                <option value="la">Louisiana</option>

                                <option value="me">Maine</option>
                                <option value="md">Maryland</option>
                                <option value="ma">Massachusetts</option>
                                <option value="mi">Michigan</option>
                                <option value="mn">Minnesota</option>
                                <option value="ms">Mississippi</option>
                                <option value="mo">Missouri</option>
                                <option value="mt">Montana</option>
                                <option value="ne">Nebraska</option>
                                <option value="nv">Nevada</option>
                                <option value="nh">New Hampshire</option>
                                <option value="nj">New Jersey</option>
                                <option value="nm">New Mexico</option>
                                <option value="ny">New York</option>
                                <option value="nc">North Carolina</option>
                                <option value="nd">North Dakota</option>
                                <option value="mp">Northern Mariana Islands</option>

                                <option value="oh">Ohio</option>
                                <option value="ok">Oklahoma</option>
                                <option value="or">Oregon</option>

                                <option value="pa">Pennsylvania</option>
                                <option value="pr">Puerto Rico</option>

                                <option value="ri">Rhode Island</option>

                                <option value="sc">South Carolina</option>
                                <option value="sd">South Dakota</option>

                                <option value="tn">Tennessee</option>
                                <option value="tx">Texas</option>

                                <option value="ut">Utah</option>

                                <option value="vt">Vermont</option>
                                <option value="vi">Virgin Islands</option>
                                <option value="va">Virginia</option>

                                <option value="wa">Washington</option>
                                <option value="wv">West Virginia</option>
                                <option value="wi">Wisconsin</option>
                                <option value="wy">Wyoming</option>
                            </select>
                        </div>

                        <!-- Type Drop Down -->
                        <div class="col-2">
                            <select name="type" id="type">
                                <option value="">All Types</option>

                                <option value="alr">Alerts</option>
                                <option value="art">Articles</option>
                                <option value="ev">Events</option>
                                <option value="nr">News Releases</option>
                            </select>
                        </div>

                        <!-- Search Submit Button -->
                        <div class="col-2">
                            <input type="submit" value="Search" />
                        </div>
                    </div>
                </form>

                <div class="row">
                    <%
                        ArrayList<CESearchResult> res = (ArrayList<CESearchResult>) request.getAttribute("res");
                        request.setAttribute("res", res);
                        request.setAttribute("size", res.size());
                    %>
                    <div class="col-12">
                        <h3>${size} Search Results.</h3>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12">
                        <!-- Prints result through Java servlet -->
                        <jsp:include page="CEServletPost">
                            <jsp:param name="result" value="<%=request.getAttribute(
                            \"res\")%>"/> 
                        </jsp:include>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer id="footer">
        <div class="inner">
            <div class="content">
                <!-- Left of Footer -->
                <section>
                    <h3>About this Web Application</h3>
                    <p>Created by Jennifer Carballo, the National Park Service Kiosk was built as part of Capital One's Software Engineering Summit application for the Summer of 2019.</p>
                </section>

                <!-- Blank Space -->
                <section></section>

                <!-- Right of Footer -->
                <section>
                    <h4>View The Code</h4>
                    <ul class="plain">
                        <li><a href="https://github.com/jcarballo1/NationalParkServiceKiosk"><i class="icon fa-github">&nbsp;</i>Github</a></li>
                    </ul>
                </section>
            </div>

            <div class="copyright">
                &copy; 2019 Jennifer Carballo All rights reserved. Video <a href="https://www.youtube.com/watch?v=HEZ3WyAUPZM">Amazing Places on Our Planet</a>.
            </div>
        </div>
    </footer>

    <!-- Scripts -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/browser.min.js"></script>
    <script src="assets/js/breakpoints.min.js"></script>
    <script src="assets/js/util.js"></script>
    <script src="assets/js/main.js"></script>
</body>
</html>