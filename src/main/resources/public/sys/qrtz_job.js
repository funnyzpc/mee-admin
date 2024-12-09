import * as Common  from "../common.js"
import * as Common2  from "../common.js?r=execute"
import * as FetchUtils  from "../fetch_utils.js"

// 时区
var quartz_zones = {"Asia/Shanghai":{"v":"Asia/Shanghai","l":"亚洲/上海"},"Asia/Chongqing":{"v":"Asia/Chongqing","l":"亚洲/重庆"},"Asia/Taipei":{"v":"Asia/Taipei","l":"亚洲/台北"},"America/New_York":{"v":"America/New_York","l":"美洲/纽约"},"Africa/Abidjan":{"v":"Africa/Abidjan","l":"Africa/Abidjan"},"Africa/Accra":{"v":"Africa/Accra","l":"Africa/Accra"},"Africa/Addis_Ababa":{"v":"Africa/Addis_Ababa","l":"Africa/Addis_Ababa"},"Africa/Algiers":{"v":"Africa/Algiers","l":"Africa/Algiers"},"Africa/Asmara":{"v":"Africa/Asmara","l":"Africa/Asmara"},"Africa/Asmera":{"v":"Africa/Asmera","l":"Africa/Asmera"},"Africa/Bamako":{"v":"Africa/Bamako","l":"Africa/Bamako"},"Africa/Bangui":{"v":"Africa/Bangui","l":"Africa/Bangui"},"Africa/Banjul":{"v":"Africa/Banjul","l":"Africa/Banjul"},"Africa/Bissau":{"v":"Africa/Bissau","l":"Africa/Bissau"},"Africa/Blantyre":{"v":"Africa/Blantyre","l":"Africa/Blantyre"},"Africa/Brazzaville":{"v":"Africa/Brazzaville","l":"Africa/Brazzaville"},"Africa/Bujumbura":{"v":"Africa/Bujumbura","l":"Africa/Bujumbura"},"Africa/Cairo":{"v":"Africa/Cairo","l":"Africa/Cairo"},"Africa/Casablanca":{"v":"Africa/Casablanca","l":"Africa/Casablanca"},"Africa/Ceuta":{"v":"Africa/Ceuta","l":"Africa/Ceuta"},"Africa/Conakry":{"v":"Africa/Conakry","l":"Africa/Conakry"},"Africa/Dakar":{"v":"Africa/Dakar","l":"Africa/Dakar"},"Africa/Dar_es_Salaam":{"v":"Africa/Dar_es_Salaam","l":"Africa/Dar_es_Salaam"},"Africa/Djibouti":{"v":"Africa/Djibouti","l":"Africa/Djibouti"},"Africa/Douala":{"v":"Africa/Douala","l":"Africa/Douala"},"Africa/El_Aaiun":{"v":"Africa/El_Aaiun","l":"Africa/El_Aaiun"},"Africa/Freetown":{"v":"Africa/Freetown","l":"Africa/Freetown"},"Africa/Gaborone":{"v":"Africa/Gaborone","l":"Africa/Gaborone"},"Africa/Harare":{"v":"Africa/Harare","l":"Africa/Harare"},"Africa/Johannesburg":{"v":"Africa/Johannesburg","l":"Africa/Johannesburg"},"Africa/Juba":{"v":"Africa/Juba","l":"Africa/Juba"},"Africa/Kampala":{"v":"Africa/Kampala","l":"Africa/Kampala"},"Africa/Khartoum":{"v":"Africa/Khartoum","l":"Africa/Khartoum"},"Africa/Kigali":{"v":"Africa/Kigali","l":"Africa/Kigali"},"Africa/Kinshasa":{"v":"Africa/Kinshasa","l":"Africa/Kinshasa"},"Africa/Lagos":{"v":"Africa/Lagos","l":"Africa/Lagos"},"Africa/Libreville":{"v":"Africa/Libreville","l":"Africa/Libreville"},"Africa/Lome":{"v":"Africa/Lome","l":"Africa/Lome"},"Africa/Luanda":{"v":"Africa/Luanda","l":"Africa/Luanda"},"Africa/Lubumbashi":{"v":"Africa/Lubumbashi","l":"Africa/Lubumbashi"},"Africa/Lusaka":{"v":"Africa/Lusaka","l":"Africa/Lusaka"},"Africa/Malabo":{"v":"Africa/Malabo","l":"Africa/Malabo"},"Africa/Maputo":{"v":"Africa/Maputo","l":"Africa/Maputo"},"Africa/Maseru":{"v":"Africa/Maseru","l":"Africa/Maseru"},"Africa/Mbabane":{"v":"Africa/Mbabane","l":"Africa/Mbabane"},"Africa/Mogadishu":{"v":"Africa/Mogadishu","l":"Africa/Mogadishu"},"Africa/Monrovia":{"v":"Africa/Monrovia","l":"Africa/Monrovia"},"Africa/Nairobi":{"v":"Africa/Nairobi","l":"Africa/Nairobi"},"Africa/Ndjamena":{"v":"Africa/Ndjamena","l":"Africa/Ndjamena"},"Africa/Niamey":{"v":"Africa/Niamey","l":"Africa/Niamey"},"Africa/Nouakchott":{"v":"Africa/Nouakchott","l":"Africa/Nouakchott"},"Africa/Ouagadougou":{"v":"Africa/Ouagadougou","l":"Africa/Ouagadougou"},"Africa/Porto-Novo":{"v":"Africa/Porto-Novo","l":"Africa/Porto-Novo"},"Africa/Sao_Tome":{"v":"Africa/Sao_Tome","l":"Africa/Sao_Tome"},"Africa/Timbuktu":{"v":"Africa/Timbuktu","l":"Africa/Timbuktu"},"Africa/Tripoli":{"v":"Africa/Tripoli","l":"Africa/Tripoli"},"Africa/Tunis":{"v":"Africa/Tunis","l":"Africa/Tunis"},"Africa/Windhoek":{"v":"Africa/Windhoek","l":"Africa/Windhoek"},"America/Adak":{"v":"America/Adak","l":"America/Adak"},"America/Anchorage":{"v":"America/Anchorage","l":"America/Anchorage"},"America/Anguilla":{"v":"America/Anguilla","l":"America/Anguilla"},"America/Antigua":{"v":"America/Antigua","l":"America/Antigua"},"America/Araguaina":{"v":"America/Araguaina","l":"America/Araguaina"},"America/Argentina/Buenos_Aires":{"v":"America/Argentina/Buenos_Aires","l":"America/Argentina/Buenos_Aires"},"America/Argentina/Catamarca":{"v":"America/Argentina/Catamarca","l":"America/Argentina/Catamarca"},"America/Argentina/ComodRivadavia":{"v":"America/Argentina/ComodRivadavia","l":"America/Argentina/ComodRivadavia"},"America/Argentina/Cordoba":{"v":"America/Argentina/Cordoba","l":"America/Argentina/Cordoba"},"America/Argentina/Jujuy":{"v":"America/Argentina/Jujuy","l":"America/Argentina/Jujuy"},"America/Argentina/La_Rioja":{"v":"America/Argentina/La_Rioja","l":"America/Argentina/La_Rioja"},"America/Argentina/Mendoza":{"v":"America/Argentina/Mendoza","l":"America/Argentina/Mendoza"},"America/Argentina/Rio_Gallegos":{"v":"America/Argentina/Rio_Gallegos","l":"America/Argentina/Rio_Gallegos"},"America/Argentina/Salta":{"v":"America/Argentina/Salta","l":"America/Argentina/Salta"},"America/Argentina/San_Juan":{"v":"America/Argentina/San_Juan","l":"America/Argentina/San_Juan"},"America/Argentina/San_Luis":{"v":"America/Argentina/San_Luis","l":"America/Argentina/San_Luis"},"America/Argentina/Tucuman":{"v":"America/Argentina/Tucuman","l":"America/Argentina/Tucuman"},"America/Argentina/Ushuaia":{"v":"America/Argentina/Ushuaia","l":"America/Argentina/Ushuaia"},"America/Aruba":{"v":"America/Aruba","l":"America/Aruba"},"America/Asuncion":{"v":"America/Asuncion","l":"America/Asuncion"},"America/Atikokan":{"v":"America/Atikokan","l":"America/Atikokan"},"America/Atka":{"v":"America/Atka","l":"America/Atka"},"America/Bahia":{"v":"America/Bahia","l":"America/Bahia"},"America/Bahia_Banderas":{"v":"America/Bahia_Banderas","l":"America/Bahia_Banderas"},"America/Barbados":{"v":"America/Barbados","l":"America/Barbados"},"America/Belem":{"v":"America/Belem","l":"America/Belem"},"America/Belize":{"v":"America/Belize","l":"America/Belize"},"America/Blanc-Sablon":{"v":"America/Blanc-Sablon","l":"America/Blanc-Sablon"},"America/Boa_Vista":{"v":"America/Boa_Vista","l":"America/Boa_Vista"},"America/Bogota":{"v":"America/Bogota","l":"America/Bogota"},"America/Boise":{"v":"America/Boise","l":"America/Boise"},"America/Buenos_Aires":{"v":"America/Buenos_Aires","l":"America/Buenos_Aires"},"America/Cambridge_Bay":{"v":"America/Cambridge_Bay","l":"America/Cambridge_Bay"},"America/Campo_Grande":{"v":"America/Campo_Grande","l":"America/Campo_Grande"},"America/Cancun":{"v":"America/Cancun","l":"America/Cancun"},"America/Caracas":{"v":"America/Caracas","l":"America/Caracas"},"America/Catamarca":{"v":"America/Catamarca","l":"America/Catamarca"},"America/Cayenne":{"v":"America/Cayenne","l":"America/Cayenne"},"America/Cayman":{"v":"America/Cayman","l":"America/Cayman"},"America/Chicago":{"v":"America/Chicago","l":"America/Chicago"},"America/Chihuahua":{"v":"America/Chihuahua","l":"America/Chihuahua"},"America/Coral_Harbour":{"v":"America/Coral_Harbour","l":"America/Coral_Harbour"},"America/Cordoba":{"v":"America/Cordoba","l":"America/Cordoba"},"America/Costa_Rica":{"v":"America/Costa_Rica","l":"America/Costa_Rica"},"America/Creston":{"v":"America/Creston","l":"America/Creston"},"America/Cuiaba":{"v":"America/Cuiaba","l":"America/Cuiaba"},"America/Curacao":{"v":"America/Curacao","l":"America/Curacao"},"America/Danmarkshavn":{"v":"America/Danmarkshavn","l":"America/Danmarkshavn"},"America/Dawson":{"v":"America/Dawson","l":"America/Dawson"},"America/Dawson_Creek":{"v":"America/Dawson_Creek","l":"America/Dawson_Creek"},"America/Denver":{"v":"America/Denver","l":"America/Denver"},"America/Detroit":{"v":"America/Detroit","l":"America/Detroit"},"America/Dominica":{"v":"America/Dominica","l":"America/Dominica"},"America/Edmonton":{"v":"America/Edmonton","l":"America/Edmonton"},"America/Eirunepe":{"v":"America/Eirunepe","l":"America/Eirunepe"},"America/El_Salvador":{"v":"America/El_Salvador","l":"America/El_Salvador"},"America/Ensenada":{"v":"America/Ensenada","l":"America/Ensenada"},"America/Fort_Nelson":{"v":"America/Fort_Nelson","l":"America/Fort_Nelson"},"America/Fort_Wayne":{"v":"America/Fort_Wayne","l":"America/Fort_Wayne"},"America/Fortaleza":{"v":"America/Fortaleza","l":"America/Fortaleza"},"America/Glace_Bay":{"v":"America/Glace_Bay","l":"America/Glace_Bay"},"America/Godthab":{"v":"America/Godthab","l":"America/Godthab"},"America/Goose_Bay":{"v":"America/Goose_Bay","l":"America/Goose_Bay"},"America/Grand_Turk":{"v":"America/Grand_Turk","l":"America/Grand_Turk"},"America/Grenada":{"v":"America/Grenada","l":"America/Grenada"},"America/Guadeloupe":{"v":"America/Guadeloupe","l":"America/Guadeloupe"},"America/Guatemala":{"v":"America/Guatemala","l":"America/Guatemala"},"America/Guayaquil":{"v":"America/Guayaquil","l":"America/Guayaquil"},"America/Guyana":{"v":"America/Guyana","l":"America/Guyana"},"America/Halifax":{"v":"America/Halifax","l":"America/Halifax"},"America/Havana":{"v":"America/Havana","l":"America/Havana"},"America/Hermosillo":{"v":"America/Hermosillo","l":"America/Hermosillo"},"America/Indiana/Indianapolis":{"v":"America/Indiana/Indianapolis","l":"America/Indiana/Indianapolis"},"America/Indiana/Knox":{"v":"America/Indiana/Knox","l":"America/Indiana/Knox"},"America/Indiana/Marengo":{"v":"America/Indiana/Marengo","l":"America/Indiana/Marengo"},"America/Indiana/Petersburg":{"v":"America/Indiana/Petersburg","l":"America/Indiana/Petersburg"},"America/Indiana/Tell_City":{"v":"America/Indiana/Tell_City","l":"America/Indiana/Tell_City"},"America/Indiana/Vevay":{"v":"America/Indiana/Vevay","l":"America/Indiana/Vevay"},"America/Indiana/Vincennes":{"v":"America/Indiana/Vincennes","l":"America/Indiana/Vincennes"},"America/Indiana/Winamac":{"v":"America/Indiana/Winamac","l":"America/Indiana/Winamac"},"America/Indianapolis":{"v":"America/Indianapolis","l":"America/Indianapolis"},"America/Inuvik":{"v":"America/Inuvik","l":"America/Inuvik"},"America/Iqaluit":{"v":"America/Iqaluit","l":"America/Iqaluit"},"America/Jamaica":{"v":"America/Jamaica","l":"America/Jamaica"},"America/Jujuy":{"v":"America/Jujuy","l":"America/Jujuy"},"America/Juneau":{"v":"America/Juneau","l":"America/Juneau"},"America/Kentucky/Louisville":{"v":"America/Kentucky/Louisville","l":"America/Kentucky/Louisville"},"America/Kentucky/Monticello":{"v":"America/Kentucky/Monticello","l":"America/Kentucky/Monticello"},"America/Knox_IN":{"v":"America/Knox_IN","l":"America/Knox_IN"},"America/Kralendijk":{"v":"America/Kralendijk","l":"America/Kralendijk"},"America/La_Paz":{"v":"America/La_Paz","l":"America/La_Paz"},"America/Lima":{"v":"America/Lima","l":"America/Lima"},"America/Los_Angeles":{"v":"America/Los_Angeles","l":"America/Los_Angeles"},"America/Louisville":{"v":"America/Louisville","l":"America/Louisville"},"America/Lower_Princes":{"v":"America/Lower_Princes","l":"America/Lower_Princes"},"America/Maceio":{"v":"America/Maceio","l":"America/Maceio"},"America/Managua":{"v":"America/Managua","l":"America/Managua"},"America/Manaus":{"v":"America/Manaus","l":"America/Manaus"},"America/Marigot":{"v":"America/Marigot","l":"America/Marigot"},"America/Martinique":{"v":"America/Martinique","l":"America/Martinique"},"America/Matamoros":{"v":"America/Matamoros","l":"America/Matamoros"},"America/Mazatlan":{"v":"America/Mazatlan","l":"America/Mazatlan"},"America/Mendoza":{"v":"America/Mendoza","l":"America/Mendoza"},"America/Menominee":{"v":"America/Menominee","l":"America/Menominee"},"America/Merida":{"v":"America/Merida","l":"America/Merida"},"America/Metlakatla":{"v":"America/Metlakatla","l":"America/Metlakatla"},"America/Mexico_City":{"v":"America/Mexico_City","l":"America/Mexico_City"},"America/Miquelon":{"v":"America/Miquelon","l":"America/Miquelon"},"America/Moncton":{"v":"America/Moncton","l":"America/Moncton"},"America/Monterrey":{"v":"America/Monterrey","l":"America/Monterrey"},"America/Montevideo":{"v":"America/Montevideo","l":"America/Montevideo"},"America/Montreal":{"v":"America/Montreal","l":"America/Montreal"},"America/Montserrat":{"v":"America/Montserrat","l":"America/Montserrat"},"America/Nassau":{"v":"America/Nassau","l":"America/Nassau"},"America/Nipigon":{"v":"America/Nipigon","l":"美洲/Nipigon"},"America/Nome":{"v":"America/Nome","l":"美洲/Nome"},"America/Noronha":{"v":"America/Noronha","l":"美洲/Noronha"},"America/North_Dakota/Beulah":{"v":"America/North_Dakota/Beulah","l":"美洲/North_Dakota/Beulah"},"America/North_Dakota/Center":{"v":"America/North_Dakota/Center","l":"美洲/North_Dakota/Center"},"America/North_Dakota/New_Salem":{"v":"America/North_Dakota/New_Salem","l":"美洲/North_Dakota/New_Salem"},"America/Nuuk":{"v":"America/Nuuk","l":"America/Nuuk"},"America/Ojinaga":{"v":"America/Ojinaga","l":"America/Ojinaga"},"America/Panama":{"v":"America/Panama","l":"America/Panama"},"America/Pangnirtung":{"v":"America/Pangnirtung","l":"America/Pangnirtung"},"America/Paramaribo":{"v":"America/Paramaribo","l":"America/Paramaribo"},"America/Phoenix":{"v":"America/Phoenix","l":"America/Phoenix"},"America/Port-au-Prince":{"v":"America/Port-au-Prince","l":"America/Port-au-Prince"},"America/Port_of_Spain":{"v":"America/Port_of_Spain","l":"America/Port_of_Spain"},"America/Porto_Acre":{"v":"America/Porto_Acre","l":"America/Porto_Acre"},"America/Porto_Velho":{"v":"America/Porto_Velho","l":"America/Porto_Velho"},"America/Puerto_Rico":{"v":"America/Puerto_Rico","l":"America/Puerto_Rico"},"America/Punta_Arenas":{"v":"America/Punta_Arenas","l":"America/Punta_Arenas"},"America/Rainy_River":{"v":"America/Rainy_River","l":"America/Rainy_River"},"America/Rankin_Inlet":{"v":"America/Rankin_Inlet","l":"America/Rankin_Inlet"},"America/Recife":{"v":"America/Recife","l":"America/Recife"},"America/Regina":{"v":"America/Regina","l":"America/Regina"},"America/Resolute":{"v":"America/Resolute","l":"America/Resolute"},"America/Rio_Branco":{"v":"America/Rio_Branco","l":"America/Rio_Branco"},"America/Rosario":{"v":"America/Rosario","l":"America/Rosario"},"America/Santa_Isabel":{"v":"America/Santa_Isabel","l":"America/Santa_Isabel"},"America/Santarem":{"v":"America/Santarem","l":"America/Santarem"},"America/Santiago":{"v":"America/Santiago","l":"America/Santiago"},"America/Santo_Domingo":{"v":"America/Santo_Domingo","l":"America/Santo_Domingo"},"America/Sao_Paulo":{"v":"America/Sao_Paulo","l":"America/Sao_Paulo"},"America/Scoresbysund":{"v":"America/Scoresbysund","l":"America/Scoresbysund"},"America/Shiprock":{"v":"America/Shiprock","l":"America/Shiprock"},"America/Sitka":{"v":"America/Sitka","l":"America/Sitka"},"America/St_Barthelemy":{"v":"America/St_Barthelemy","l":"America/St_Barthelemy"},"America/St_Johns":{"v":"America/St_Johns","l":"America/St_Johns"},"America/St_Kitts":{"v":"America/St_Kitts","l":"America/St_Kitts"},"America/St_Lucia":{"v":"America/St_Lucia","l":"America/St_Lucia"},"America/St_Thomas":{"v":"America/St_Thomas","l":"America/St_Thomas"},"America/St_Vincent":{"v":"America/St_Vincent","l":"America/St_Vincent"},"America/Swift_Current":{"v":"America/Swift_Current","l":"America/Swift_Current"},"America/Tegucigalpa":{"v":"America/Tegucigalpa","l":"America/Tegucigalpa"},"America/Thule":{"v":"America/Thule","l":"America/Thule"},"America/Thunder_Bay":{"v":"America/Thunder_Bay","l":"America/Thunder_Bay"},"America/Tijuana":{"v":"America/Tijuana","l":"America/Tijuana"},"America/Toronto":{"v":"America/Toronto","l":"America/Toronto"},"America/Tortola":{"v":"America/Tortola","l":"America/Tortola"},"America/Vancouver":{"v":"America/Vancouver","l":"America/Vancouver"},"America/Virgin":{"v":"America/Virgin","l":"America/Virgin"},"America/Whitehorse":{"v":"America/Whitehorse","l":"America/Whitehorse"},"America/Winnipeg":{"v":"America/Winnipeg","l":"America/Winnipeg"},"America/Yakutat":{"v":"America/Yakutat","l":"America/Yakutat"},"America/Yellowknife":{"v":"America/Yellowknife","l":"America/Yellowknife"},"Antarctica/Casey":{"v":"Antarctica/Casey","l":"Antarctica/Casey"},"Antarctica/Davis":{"v":"Antarctica/Davis","l":"Antarctica/Davis"},"Antarctica/DumontDUrville":{"v":"Antarctica/DumontDUrville","l":"Antarctica/DumontDUrville"},"Antarctica/Macquarie":{"v":"Antarctica/Macquarie","l":"Antarctica/Macquarie"},"Antarctica/Mawson":{"v":"Antarctica/Mawson","l":"Antarctica/Mawson"},"Antarctica/McMurdo":{"v":"Antarctica/McMurdo","l":"Antarctica/McMurdo"},"Antarctica/Palmer":{"v":"Antarctica/Palmer","l":"Antarctica/Palmer"},"Antarctica/Rothera":{"v":"Antarctica/Rothera","l":"Antarctica/Rothera"},"Antarctica/South_Pole":{"v":"Antarctica/South_Pole","l":"Antarctica/South_Pole"},"Antarctica/Syowa":{"v":"Antarctica/Syowa","l":"Antarctica/Syowa"},"Antarctica/Troll":{"v":"Antarctica/Troll","l":"Antarctica/Troll"},"Antarctica/Vostok":{"v":"Antarctica/Vostok","l":"Antarctica/Vostok"},"Arctic/Longyearbyen":{"v":"Arctic/Longyearbyen","l":"Arctic/Longyearbyen"},"Asia/Aden":{"v":"Asia/Aden","l":"Asia/Aden"},"Asia/Almaty":{"v":"Asia/Almaty","l":"Asia/Almaty"},"Asia/Amman":{"v":"Asia/Amman","l":"Asia/Amman"},"Asia/Anadyr":{"v":"Asia/Anadyr","l":"Asia/Anadyr"},"Asia/Aqtau":{"v":"Asia/Aqtau","l":"Asia/Aqtau"},"Asia/Aqtobe":{"v":"Asia/Aqtobe","l":"Asia/Aqtobe"},"Asia/Ashgabat":{"v":"Asia/Ashgabat","l":"Asia/Ashgabat"},"Asia/Ashkhabad":{"v":"Asia/Ashkhabad","l":"Asia/Ashkhabad"},"Asia/Atyrau":{"v":"Asia/Atyrau","l":"Asia/Atyrau"},"Asia/Baghdad":{"v":"Asia/Baghdad","l":"Asia/Baghdad"},"Asia/Bahrain":{"v":"Asia/Bahrain","l":"Asia/Bahrain"},"Asia/Baku":{"v":"Asia/Baku","l":"Asia/Baku"},"Asia/Bangkok":{"v":"Asia/Bangkok","l":"Asia/Bangkok"},"Asia/Barnaul":{"v":"Asia/Barnaul","l":"Asia/Barnaul"},"Asia/Beirut":{"v":"Asia/Beirut","l":"Asia/Beirut"},"Asia/Bishkek":{"v":"Asia/Bishkek","l":"Asia/Bishkek"},"Asia/Brunei":{"v":"Asia/Brunei","l":"Asia/Brunei"},"Asia/Calcutta":{"v":"Asia/Calcutta","l":"Asia/Calcutta"},"Asia/Chita":{"v":"Asia/Chita","l":"Asia/Chita"},"Asia/Choibalsan":{"v":"Asia/Choibalsan","l":"Asia/Choibalsan"},"Asia/Chungking":{"v":"Asia/Chungking","l":"Asia/Chungking"},"Asia/Colombo":{"v":"Asia/Colombo","l":"Asia/Colombo"},"Asia/Dacca":{"v":"Asia/Dacca","l":"Asia/Dacca"},"Asia/Damascus":{"v":"Asia/Damascus","l":"Asia/Damascus"},"Asia/Dhaka":{"v":"Asia/Dhaka","l":"Asia/Dhaka"},"Asia/Dili":{"v":"Asia/Dili","l":"Asia/Dili"},"Asia/Dubai":{"v":"Asia/Dubai","l":"Asia/Dubai"},"Asia/Dushanbe":{"v":"Asia/Dushanbe","l":"Asia/Dushanbe"},"Asia/Famagusta":{"v":"Asia/Famagusta","l":"Asia/Famagusta"},"Asia/Gaza":{"v":"Asia/Gaza","l":"Asia/Gaza"},"Asia/Harbin":{"v":"Asia/Harbin","l":"Asia/Harbin"},"Asia/Hebron":{"v":"Asia/Hebron","l":"Asia/Hebron"},"Asia/Ho_Chi_Minh":{"v":"Asia/Ho_Chi_Minh","l":"Asia/Ho_Chi_Minh"},"Asia/Hong_Kong":{"v":"Asia/Hong_Kong","l":"Asia/Hong_Kong"},"Asia/Hovd":{"v":"Asia/Hovd","l":"Asia/Hovd"},"Asia/Irkutsk":{"v":"Asia/Irkutsk","l":"Asia/Irkutsk"},"Asia/Istanbul":{"v":"Asia/Istanbul","l":"Asia/Istanbul"},"Asia/Jakarta":{"v":"Asia/Jakarta","l":"Asia/Jakarta"},"Asia/Jayapura":{"v":"Asia/Jayapura","l":"Asia/Jayapura"},"Asia/Jerusalem":{"v":"Asia/Jerusalem","l":"Asia/Jerusalem"},"Asia/Kabul":{"v":"Asia/Kabul","l":"Asia/Kabul"},"Asia/Kamchatka":{"v":"Asia/Kamchatka","l":"Asia/Kamchatka"},"Asia/Karachi":{"v":"Asia/Karachi","l":"Asia/Karachi"},"Asia/Kashgar":{"v":"Asia/Kashgar","l":"Asia/Kashgar"},"Asia/Kathmandu":{"v":"Asia/Kathmandu","l":"Asia/Kathmandu"},"Asia/Katmandu":{"v":"Asia/Katmandu","l":"Asia/Katmandu"},"Asia/Khandyga":{"v":"Asia/Khandyga","l":"Asia/Khandyga"},"Asia/Kolkata":{"v":"Asia/Kolkata","l":"Asia/Kolkata"},"Asia/Krasnoyarsk":{"v":"Asia/Krasnoyarsk","l":"Asia/Krasnoyarsk"},"Asia/Kuala_Lumpur":{"v":"Asia/Kuala_Lumpur","l":"Asia/Kuala_Lumpur"},"Asia/Kuching":{"v":"Asia/Kuching","l":"Asia/Kuching"},"Asia/Kuwait":{"v":"Asia/Kuwait","l":"Asia/Kuwait"},"Asia/Macao":{"v":"Asia/Macao","l":"Asia/Macao"},"Asia/Macau":{"v":"Asia/Macau","l":"Asia/Macau"},"Asia/Magadan":{"v":"Asia/Magadan","l":"Asia/Magadan"},"Asia/Makassar":{"v":"Asia/Makassar","l":"Asia/Makassar"},"Asia/Manila":{"v":"Asia/Manila","l":"Asia/Manila"},"Asia/Muscat":{"v":"Asia/Muscat","l":"Asia/Muscat"},"Asia/Nicosia":{"v":"Asia/Nicosia","l":"Asia/Nicosia"},"Asia/Novokuznetsk":{"v":"Asia/Novokuznetsk","l":"Asia/Novokuznetsk"},"Asia/Novosibirsk":{"v":"Asia/Novosibirsk","l":"Asia/Novosibirsk"},"Asia/Omsk":{"v":"Asia/Omsk","l":"Asia/Omsk"},"Asia/Oral":{"v":"Asia/Oral","l":"Asia/Oral"},"Asia/Phnom_Penh":{"v":"Asia/Phnom_Penh","l":"Asia/Phnom_Penh"},"Asia/Pontianak":{"v":"Asia/Pontianak","l":"Asia/Pontianak"},"Asia/Pyongyang":{"v":"Asia/Pyongyang","l":"Asia/Pyongyang"},"Asia/Qatar":{"v":"Asia/Qatar","l":"Asia/Qatar"},"Asia/Qostanay":{"v":"Asia/Qostanay","l":"Asia/Qostanay"},"Asia/Qyzylorda":{"v":"Asia/Qyzylorda","l":"Asia/Qyzylorda"},"Asia/Rangoon":{"v":"Asia/Rangoon","l":"Asia/Rangoon"},"Asia/Riyadh":{"v":"Asia/Riyadh","l":"Asia/Riyadh"},"Asia/Saigon":{"v":"Asia/Saigon","l":"Asia/Saigon"},"Asia/Sakhalin":{"v":"Asia/Sakhalin","l":"Asia/Sakhalin"},"Asia/Samarkand":{"v":"Asia/Samarkand","l":"Asia/Samarkand"},"Asia/Seoul":{"v":"Asia/Seoul","l":"Asia/Seoul"},"Asia/Singapore":{"v":"Asia/Singapore","l":"亚洲/新加坡"},"Asia/Srednekolymsk":{"v":"Asia/Srednekolymsk","l":"Asia/Srednekolymsk"},"Asia/Tashkent":{"v":"Asia/Tashkent","l":"Asia/Tashkent"},"Asia/Tbilisi":{"v":"Asia/Tbilisi","l":"Asia/Tbilisi"},"Asia/Tehran":{"v":"Asia/Tehran","l":"Asia/Tehran"},"Asia/Tel_Aviv":{"v":"Asia/Tel_Aviv","l":"Asia/Tel_Aviv"},"Asia/Thimbu":{"v":"Asia/Thimbu","l":"Asia/Thimbu"},"Asia/Thimphu":{"v":"Asia/Thimphu","l":"Asia/Thimphu"},"Asia/Tokyo":{"v":"Asia/Tokyo","l":"Asia/Tokyo"},"Asia/Tomsk":{"v":"Asia/Tomsk","l":"Asia/Tomsk"},"Asia/Ujung_Pandang":{"v":"Asia/Ujung_Pandang","l":"Asia/Ujung_Pandang"},"Asia/Ulaanbaatar":{"v":"Asia/Ulaanbaatar","l":"Asia/Ulaanbaatar"},"Asia/Ulan_Bator":{"v":"Asia/Ulan_Bator","l":"Asia/Ulan_Bator"},"Asia/Urumqi":{"v":"Asia/Urumqi","l":"Asia/Urumqi"},"Asia/Ust-Nera":{"v":"Asia/Ust-Nera","l":"Asia/Ust-Nera"},"Asia/Vientiane":{"v":"Asia/Vientiane","l":"Asia/Vientiane"},"Asia/Vladivostok":{"v":"Asia/Vladivostok","l":"Asia/Vladivostok"},"Asia/Yakutsk":{"v":"Asia/Yakutsk","l":"Asia/Yakutsk"},"Asia/Yangon":{"v":"Asia/Yangon","l":"Asia/Yangon"},"Asia/Yekaterinburg":{"v":"Asia/Yekaterinburg","l":"Asia/Yekaterinburg"},"Asia/Yerevan":{"v":"Asia/Yerevan","l":"Asia/Yerevan"},"Atlantic/Azores":{"v":"Atlantic/Azores","l":"Atlantic/Azores"},"Atlantic/Bermuda":{"v":"Atlantic/Bermuda","l":"Atlantic/Bermuda"},"Atlantic/Canary":{"v":"Atlantic/Canary","l":"Atlantic/Canary"},"Atlantic/Cape_Verde":{"v":"Atlantic/Cape_Verde","l":"Atlantic/Cape_Verde"},"Atlantic/Faeroe":{"v":"Atlantic/Faeroe","l":"Atlantic/Faeroe"},"Atlantic/Faroe":{"v":"Atlantic/Faroe","l":"Atlantic/Faroe"},"Atlantic/Jan_Mayen":{"v":"Atlantic/Jan_Mayen","l":"Atlantic/Jan_Mayen"},"Atlantic/Madeira":{"v":"Atlantic/Madeira","l":"Atlantic/Madeira"},"Atlantic/Reykjavik":{"v":"Atlantic/Reykjavik","l":"Atlantic/Reykjavik"},"Atlantic/South_Georgia":{"v":"Atlantic/South_Georgia","l":"Atlantic/South_Georgia"},"Atlantic/St_Helena":{"v":"Atlantic/St_Helena","l":"Atlantic/St_Helena"},"Atlantic/Stanley":{"v":"Atlantic/Stanley","l":"Atlantic/Stanley"},"Australia/ACT":{"v":"Australia/ACT","l":"Australia/ACT"},"Australia/Adelaide":{"v":"Australia/Adelaide","l":"Australia/Adelaide"},"Australia/Brisbane":{"v":"Australia/Brisbane","l":"Australia/Brisbane"},"Australia/Broken_Hill":{"v":"Australia/Broken_Hill","l":"Australia/Broken_Hill"},"Australia/Canberra":{"v":"Australia/Canberra","l":"Australia/Canberra"},"Australia/Currie":{"v":"Australia/Currie","l":"Australia/Currie"},"Australia/Darwin":{"v":"Australia/Darwin","l":"Australia/Darwin"},"Australia/Eucla":{"v":"Australia/Eucla","l":"Australia/Eucla"},"Australia/Hobart":{"v":"Australia/Hobart","l":"Australia/Hobart"},"Australia/LHI":{"v":"Australia/LHI","l":"Australia/LHI"},"Australia/Lindeman":{"v":"Australia/Lindeman","l":"Australia/Lindeman"},"Australia/Lord_Howe":{"v":"Australia/Lord_Howe","l":"Australia/Lord_Howe"},"Australia/Melbourne":{"v":"Australia/Melbourne","l":"Australia/Melbourne"},"Australia/NSW":{"v":"Australia/NSW","l":"Australia/NSW"},"Australia/North":{"v":"Australia/North","l":"Australia/North"},"Australia/Perth":{"v":"Australia/Perth","l":"Australia/Perth"},"Australia/Queensland":{"v":"Australia/Queensland","l":"Australia/Queensland"},"Australia/South":{"v":"Australia/South","l":"Australia/South"},"Australia/Sydney":{"v":"Australia/Sydney","l":"Australia/Sydney"},"Australia/Tasmania":{"v":"Australia/Tasmania","l":"Australia/Tasmania"},"Australia/Victoria":{"v":"Australia/Victoria","l":"Australia/Victoria"},"Australia/West":{"v":"Australia/West","l":"Australia/West"},"Australia/Yancowinna":{"v":"Australia/Yancowinna","l":"Australia/Yancowinna"},"Brazil/Acre":{"v":"Brazil/Acre","l":"Brazil/Acre"},"Brazil/DeNoronha":{"v":"Brazil/DeNoronha","l":"Brazil/DeNoronha"},"Brazil/East":{"v":"Brazil/East","l":"Brazil/East"},"Brazil/West":{"v":"Brazil/West","l":"Brazil/West"},"CET":{"v":"CET","l":"CET"},"CST6CDT":{"v":"CST6CDT","l":"CST6CDT"},"Canada/Atlantic":{"v":"Canada/Atlantic","l":"Canada/Atlantic"},"Canada/Central":{"v":"Canada/Central","l":"Canada/Central"},"Canada/Eastern":{"v":"Canada/Eastern","l":"Canada/Eastern"},"Canada/Mountain":{"v":"Canada/Mountain","l":"Canada/Mountain"},"Canada/Newfoundland":{"v":"Canada/Newfoundland","l":"Canada/Newfoundland"},"Canada/Pacific":{"v":"Canada/Pacific","l":"Canada/Pacific"},"Canada/Saskatchewan":{"v":"Canada/Saskatchewan","l":"Canada/Saskatchewan"},"Canada/Yukon":{"v":"Canada/Yukon","l":"Canada/Yukon"},"Chile/Continental":{"v":"Chile/Continental","l":"Chile/Continental"},"Chile/EasterIsland":{"v":"Chile/EasterIsland","l":"Chile/EasterIsland"},"Cuba":{"v":"Cuba","l":"Cuba"},"EET":{"v":"EET","l":"EET"},"EST5EDT":{"v":"EST5EDT","l":"EST5EDT"},"Egypt":{"v":"Egypt","l":"Egypt"},"Eire":{"v":"Eire","l":"Eire"},"Etc/GMT":{"v":"Etc/GMT","l":"Etc/GMT"},"Etc/GMT+0":{"v":"Etc/GMT+0","l":"Etc/GMT+0"},"Etc/GMT+1":{"v":"Etc/GMT+1","l":"Etc/GMT+1"},"Etc/GMT+10":{"v":"Etc/GMT+10","l":"Etc/GMT+10"},"Etc/GMT+11":{"v":"Etc/GMT+11","l":"Etc/GMT+11"},"Etc/GMT+12":{"v":"Etc/GMT+12","l":"Etc/GMT+12"},"Etc/GMT+2":{"v":"Etc/GMT+2","l":"Etc/GMT+2"},"Etc/GMT+3":{"v":"Etc/GMT+3","l":"Etc/GMT+3"},"Etc/GMT+4":{"v":"Etc/GMT+4","l":"Etc/GMT+4"},"Etc/GMT+5":{"v":"Etc/GMT+5","l":"Etc/GMT+5"},"Etc/GMT+6":{"v":"Etc/GMT+6","l":"Etc/GMT+6"},"Etc/GMT+7":{"v":"Etc/GMT+7","l":"Etc/GMT+7"},"Etc/GMT+8":{"v":"Etc/GMT+8","l":"Etc/GMT+8"},"Etc/GMT+9":{"v":"Etc/GMT+9","l":"Etc/GMT+9"},"Etc/GMT-0":{"v":"Etc/GMT-0","l":"Etc/GMT-0"},"Etc/GMT-1":{"v":"Etc/GMT-1","l":"Etc/GMT-1"},"Etc/GMT-10":{"v":"Etc/GMT-10","l":"Etc/GMT-10"},"Etc/GMT-11":{"v":"Etc/GMT-11","l":"Etc/GMT-11"},"Etc/GMT-12":{"v":"Etc/GMT-12","l":"Etc/GMT-12"},"Etc/GMT-13":{"v":"Etc/GMT-13","l":"Etc/GMT-13"},"Etc/GMT-14":{"v":"Etc/GMT-14","l":"Etc/GMT-14"},"Etc/GMT-2":{"v":"Etc/GMT-2","l":"Etc/GMT-2"},"Etc/GMT-3":{"v":"Etc/GMT-3","l":"Etc/GMT-3"},"Etc/GMT-4":{"v":"Etc/GMT-4","l":"Etc/GMT-4"},"Etc/GMT-5":{"v":"Etc/GMT-5","l":"Etc/GMT-5"},"Etc/GMT-6":{"v":"Etc/GMT-6","l":"Etc/GMT-6"},"Etc/GMT-7":{"v":"Etc/GMT-7","l":"Etc/GMT-7"},"Etc/GMT-8":{"v":"Etc/GMT-8","l":"Etc/GMT-8"},"Etc/GMT-9":{"v":"Etc/GMT-9","l":"Etc/GMT-9"},"Etc/GMT0":{"v":"Etc/GMT0","l":"Etc/GMT0"},"Etc/Greenwich":{"v":"Etc/Greenwich","l":"Etc/Greenwich"},"Etc/UCT":{"v":"Etc/UCT","l":"Etc/UCT"},"Etc/UTC":{"v":"Etc/UTC","l":"Etc/UTC"},"Etc/Universal":{"v":"Etc/Universal","l":"Etc/Universal"},"Etc/Zulu":{"v":"Etc/Zulu","l":"Etc/Zulu"},"Europe/Amsterdam":{"v":"Europe/Amsterdam","l":"Europe/Amsterdam"},"Europe/Andorra":{"v":"Europe/Andorra","l":"Europe/Andorra"},"Europe/Astrakhan":{"v":"Europe/Astrakhan","l":"Europe/Astrakhan"},"Europe/Athens":{"v":"Europe/Athens","l":"Europe/Athens"},"Europe/Belfast":{"v":"Europe/Belfast","l":"Europe/Belfast"},"Europe/Belgrade":{"v":"Europe/Belgrade","l":"Europe/Belgrade"},"Europe/Berlin":{"v":"Europe/Berlin","l":"Europe/Berlin"},"Europe/Bratislava":{"v":"Europe/Bratislava","l":"Europe/Bratislava"},"Europe/Brussels":{"v":"Europe/Brussels","l":"Europe/Brussels"},"Europe/Bucharest":{"v":"Europe/Bucharest","l":"Europe/Bucharest"},"Europe/Budapest":{"v":"Europe/Budapest","l":"Europe/Budapest"},"Europe/Busingen":{"v":"Europe/Busingen","l":"Europe/Busingen"},"Europe/Chisinau":{"v":"Europe/Chisinau","l":"Europe/Chisinau"},"Europe/Copenhagen":{"v":"Europe/Copenhagen","l":"Europe/Copenhagen"},"Europe/Dublin":{"v":"Europe/Dublin","l":"Europe/Dublin"},"Europe/Gibraltar":{"v":"Europe/Gibraltar","l":"Europe/Gibraltar"},"Europe/Guernsey":{"v":"Europe/Guernsey","l":"Europe/Guernsey"},"Europe/Helsinki":{"v":"Europe/Helsinki","l":"Europe/Helsinki"},"Europe/Isle_of_Man":{"v":"Europe/Isle_of_Man","l":"Europe/Isle_of_Man"},"Europe/Istanbul":{"v":"Europe/Istanbul","l":"Europe/Istanbul"},"Europe/Jersey":{"v":"Europe/Jersey","l":"Europe/Jersey"},"Europe/Kaliningrad":{"v":"Europe/Kaliningrad","l":"Europe/Kaliningrad"},"Europe/Kiev":{"v":"Europe/Kiev","l":"Europe/Kiev"},"Europe/Kirov":{"v":"Europe/Kirov","l":"Europe/Kirov"},"Europe/Kyiv":{"v":"Europe/Kyiv","l":"Europe/Kyiv"},"Europe/Lisbon":{"v":"Europe/Lisbon","l":"Europe/Lisbon"},"Europe/Ljubljana":{"v":"Europe/Ljubljana","l":"Europe/Ljubljana"},"Europe/London":{"v":"Europe/London","l":"Europe/London"},"Europe/Luxembourg":{"v":"Europe/Luxembourg","l":"Europe/Luxembourg"},"Europe/Madrid":{"v":"Europe/Madrid","l":"Europe/Madrid"},"Europe/Malta":{"v":"Europe/Malta","l":"Europe/Malta"},"Europe/Mariehamn":{"v":"Europe/Mariehamn","l":"Europe/Mariehamn"},"Europe/Minsk":{"v":"Europe/Minsk","l":"Europe/Minsk"},"Europe/Monaco":{"v":"Europe/Monaco","l":"Europe/Monaco"},"Europe/Moscow":{"v":"Europe/Moscow","l":"Europe/Moscow"},"Europe/Nicosia":{"v":"Europe/Nicosia","l":"Europe/Nicosia"},"Europe/Oslo":{"v":"Europe/Oslo","l":"Europe/Oslo"},"Europe/Paris":{"v":"Europe/Paris","l":"Europe/Paris"},"Europe/Podgorica":{"v":"Europe/Podgorica","l":"Europe/Podgorica"},"Europe/Prague":{"v":"Europe/Prague","l":"Europe/Prague"},"Europe/Riga":{"v":"Europe/Riga","l":"Europe/Riga"},"Europe/Rome":{"v":"Europe/Rome","l":"Europe/Rome"},"Europe/Samara":{"v":"Europe/Samara","l":"Europe/Samara"},"Europe/San_Marino":{"v":"Europe/San_Marino","l":"Europe/San_Marino"},"Europe/Sarajevo":{"v":"Europe/Sarajevo","l":"Europe/Sarajevo"},"Europe/Saratov":{"v":"Europe/Saratov","l":"Europe/Saratov"},"Europe/Simferopol":{"v":"Europe/Simferopol","l":"Europe/Simferopol"},"Europe/Skopje":{"v":"Europe/Skopje","l":"Europe/Skopje"},"Europe/Sofia":{"v":"Europe/Sofia","l":"Europe/Sofia"},"Europe/Stockholm":{"v":"Europe/Stockholm","l":"Europe/Stockholm"},"Europe/Tallinn":{"v":"Europe/Tallinn","l":"Europe/Tallinn"},"Europe/Tirane":{"v":"Europe/Tirane","l":"Europe/Tirane"},"Europe/Tiraspol":{"v":"Europe/Tiraspol","l":"Europe/Tiraspol"},"Europe/Ulyanovsk":{"v":"Europe/Ulyanovsk","l":"Europe/Ulyanovsk"},"Europe/Uzhgorod":{"v":"Europe/Uzhgorod","l":"Europe/Uzhgorod"},"Europe/Vaduz":{"v":"Europe/Vaduz","l":"Europe/Vaduz"},"Europe/Vatican":{"v":"Europe/Vatican","l":"Europe/Vatican"},"Europe/Vienna":{"v":"Europe/Vienna","l":"Europe/Vienna"},"Europe/Vilnius":{"v":"Europe/Vilnius","l":"Europe/Vilnius"},"Europe/Volgograd":{"v":"Europe/Volgograd","l":"Europe/Volgograd"},"Europe/Warsaw":{"v":"Europe/Warsaw","l":"Europe/Warsaw"},"Europe/Zagreb":{"v":"Europe/Zagreb","l":"Europe/Zagreb"},"Europe/Zaporozhye":{"v":"Europe/Zaporozhye","l":"Europe/Zaporozhye"},"Europe/Zurich":{"v":"Europe/Zurich","l":"Europe/Zurich"},"GB":{"v":"GB","l":"GB"},"GB-Eire":{"v":"GB-Eire","l":"GB-Eire"},"GMT":{"v":"GMT","l":"GMT"},"GMT0":{"v":"GMT0","l":"GMT0"},"Greenwich":{"v":"Greenwich","l":"Greenwich"},"Hongkong":{"v":"Hongkong","l":"Hongkong"},"Iceland":{"v":"Iceland","l":"Iceland"},"Indian/Antananarivo":{"v":"Indian/Antananarivo","l":"Indian/Antananarivo"},"Indian/Chagos":{"v":"Indian/Chagos","l":"Indian/Chagos"},"Indian/Christmas":{"v":"Indian/Christmas","l":"Indian/Christmas"},"Indian/Cocos":{"v":"Indian/Cocos","l":"Indian/Cocos"},"Indian/Comoro":{"v":"Indian/Comoro","l":"Indian/Comoro"},"Indian/Kerguelen":{"v":"Indian/Kerguelen","l":"Indian/Kerguelen"},"Indian/Mahe":{"v":"Indian/Mahe","l":"Indian/Mahe"},"Indian/Maldives":{"v":"Indian/Maldives","l":"Indian/Maldives"},"Indian/Mauritius":{"v":"Indian/Mauritius","l":"Indian/Mauritius"},"Indian/Mayotte":{"v":"Indian/Mayotte","l":"Indian/Mayotte"},"Indian/Reunion":{"v":"Indian/Reunion","l":"Indian/Reunion"},"Iran":{"v":"Iran","l":"Iran"},"Israel":{"v":"Israel","l":"Israel"},"Jamaica":{"v":"Jamaica","l":"Jamaica"},"Japan":{"v":"Japan","l":"Japan"},"Kwajalein":{"v":"Kwajalein","l":"Kwajalein"},"Libya":{"v":"Libya","l":"Libya"},"MET":{"v":"MET","l":"MET"},"MST7MDT":{"v":"MST7MDT","l":"MST7MDT"},"Mexico/BajaNorte":{"v":"Mexico/BajaNorte","l":"Mexico/BajaNorte"},"Mexico/BajaSur":{"v":"Mexico/BajaSur","l":"Mexico/BajaSur"},"Mexico/General":{"v":"Mexico/General","l":"Mexico/General"},"NZ":{"v":"NZ","l":"NZ"},"NZ-CHAT":{"v":"NZ-CHAT","l":"NZ-CHAT"},"Navajo":{"v":"Navajo","l":"Navajo"},"PRC":{"v":"PRC","l":"PRC"},"PST8PDT":{"v":"PST8PDT","l":"PST8PDT"},"Pacific/Apia":{"v":"Pacific/Apia","l":"Pacific/Apia"},"Pacific/Auckland":{"v":"Pacific/Auckland","l":"Pacific/Auckland"},"Pacific/Bougainville":{"v":"Pacific/Bougainville","l":"Pacific/Bougainville"},"Pacific/Chatham":{"v":"Pacific/Chatham","l":"Pacific/Chatham"},"Pacific/Chuuk":{"v":"Pacific/Chuuk","l":"Pacific/Chuuk"},"Pacific/Easter":{"v":"Pacific/Easter","l":"Pacific/Easter"},"Pacific/Efate":{"v":"Pacific/Efate","l":"Pacific/Efate"},"Pacific/Enderbury":{"v":"Pacific/Enderbury","l":"Pacific/Enderbury"},"Pacific/Fakaofo":{"v":"Pacific/Fakaofo","l":"Pacific/Fakaofo"},"Pacific/Fiji":{"v":"Pacific/Fiji","l":"Pacific/Fiji"},"Pacific/Funafuti":{"v":"Pacific/Funafuti","l":"Pacific/Funafuti"},"Pacific/Galapagos":{"v":"Pacific/Galapagos","l":"Pacific/Galapagos"},"Pacific/Gambier":{"v":"Pacific/Gambier","l":"Pacific/Gambier"},"Pacific/Guadalcanal":{"v":"Pacific/Guadalcanal","l":"Pacific/Guadalcanal"},"Pacific/Guam":{"v":"Pacific/Guam","l":"Pacific/Guam"},"Pacific/Honolulu":{"v":"Pacific/Honolulu","l":"Pacific/Honolulu"},"Pacific/Johnston":{"v":"Pacific/Johnston","l":"Pacific/Johnston"},"Pacific/Kanton":{"v":"Pacific/Kanton","l":"Pacific/Kanton"},"Pacific/Kiritimati":{"v":"Pacific/Kiritimati","l":"Pacific/Kiritimati"},"Pacific/Kosrae":{"v":"Pacific/Kosrae","l":"Pacific/Kosrae"},"Pacific/Kwajalein":{"v":"Pacific/Kwajalein","l":"Pacific/Kwajalein"},"Pacific/Majuro":{"v":"Pacific/Majuro","l":"Pacific/Majuro"},"Pacific/Marquesas":{"v":"Pacific/Marquesas","l":"Pacific/Marquesas"},"Pacific/Midway":{"v":"Pacific/Midway","l":"Pacific/Midway"},"Pacific/Nauru":{"v":"Pacific/Nauru","l":"Pacific/Nauru"},"Pacific/Niue":{"v":"Pacific/Niue","l":"Pacific/Niue"},"Pacific/Norfolk":{"v":"Pacific/Norfolk","l":"Pacific/Norfolk"},"Pacific/Noumea":{"v":"Pacific/Noumea","l":"Pacific/Noumea"},"Pacific/Pago_Pago":{"v":"Pacific/Pago_Pago","l":"Pacific/Pago_Pago"},"Pacific/Palau":{"v":"Pacific/Palau","l":"Pacific/Palau"},"Pacific/Pitcairn":{"v":"Pacific/Pitcairn","l":"Pacific/Pitcairn"},"Pacific/Pohnpei":{"v":"Pacific/Pohnpei","l":"Pacific/Pohnpei"},"Pacific/Ponape":{"v":"Pacific/Ponape","l":"Pacific/Ponape"},"Pacific/Port_Moresby":{"v":"Pacific/Port_Moresby","l":"Pacific/Port_Moresby"},"Pacific/Rarotonga":{"v":"Pacific/Rarotonga","l":"Pacific/Rarotonga"},"Pacific/Saipan":{"v":"Pacific/Saipan","l":"Pacific/Saipan"},"Pacific/Samoa":{"v":"Pacific/Samoa","l":"Pacific/Samoa"},"Pacific/Tahiti":{"v":"Pacific/Tahiti","l":"Pacific/Tahiti"},"Pacific/Tarawa":{"v":"Pacific/Tarawa","l":"Pacific/Tarawa"},"Pacific/Tongatapu":{"v":"Pacific/Tongatapu","l":"Pacific/Tongatapu"},"Pacific/Truk":{"v":"Pacific/Truk","l":"Pacific/Truk"},"Pacific/Wake":{"v":"Pacific/Wake","l":"Pacific/Wake"},"Pacific/Wallis":{"v":"Pacific/Wallis","l":"Pacific/Wallis"},"Pacific/Yap":{"v":"Pacific/Yap","l":"Pacific/Yap"},"Poland":{"v":"Poland","l":"Poland"},"Portugal":{"v":"Portugal","l":"Portugal"},"ROK":{"v":"ROK","l":"ROK"},"Singapore":{"v":"Singapore","l":"Singapore"},"SystemV/AST4":{"v":"SystemV/AST4","l":"SystemV/AST4"},"SystemV/AST4ADT":{"v":"SystemV/AST4ADT","l":"SystemV/AST4ADT"},"SystemV/CST6":{"v":"SystemV/CST6","l":"SystemV/CST6"},"SystemV/CST6CDT":{"v":"SystemV/CST6CDT","l":"SystemV/CST6CDT"},"SystemV/EST5":{"v":"SystemV/EST5","l":"SystemV/EST5"},"SystemV/EST5EDT":{"v":"SystemV/EST5EDT","l":"SystemV/EST5EDT"},"SystemV/HST10":{"v":"SystemV/HST10","l":"SystemV/HST10"},"SystemV/MST7":{"v":"SystemV/MST7","l":"SystemV/MST7"},"SystemV/MST7MDT":{"v":"SystemV/MST7MDT","l":"SystemV/MST7MDT"},"SystemV/PST8":{"v":"SystemV/PST8","l":"SystemV/PST8"},"SystemV/PST8PDT":{"v":"SystemV/PST8PDT","l":"SystemV/PST8PDT"},"SystemV/YST9":{"v":"SystemV/YST9","l":"SystemV/YST9"},"SystemV/YST9YDT":{"v":"SystemV/YST9YDT","l":"SystemV/YST9YDT"},"Turkey":{"v":"Turkey","l":"Turkey"},"UCT":{"v":"UCT","l":"UCT"},"US/Alaska":{"v":"US/Alaska","l":"US/Alaska"},"US/Aleutian":{"v":"US/Aleutian","l":"US/Aleutian"},"US/Arizona":{"v":"US/Arizona","l":"US/Arizona"},"US/Central":{"v":"US/Central","l":"US/Central"},"US/East-Indiana":{"v":"US/East-Indiana","l":"US/East-Indiana"},"US/Eastern":{"v":"US/Eastern","l":"US/Eastern"},"US/Hawaii":{"v":"US/Hawaii","l":"US/Hawaii"},"US/Indiana-Starke":{"v":"US/Indiana-Starke","l":"US/Indiana-Starke"},"US/Michigan":{"v":"US/Michigan","l":"US/Michigan"},"US/Mountain":{"v":"US/Mountain","l":"US/Mountain"},"US/Pacific":{"v":"US/Pacific","l":"US/Pacific"},"US/Samoa":{"v":"US/Samoa","l":"US/Samoa"},"UTC":{"v":"UTC","l":"UTC"},"Universal":{"v":"Universal","l":"Universal"},"W-SU":{"v":"W-SU","l":"W-SU"},"WET":{"v":"WET","l":"WET"},"Zulu":{"v":"Zulu","l":"Zulu"}};
// 表单参数 这个参数如有表单则必须定义，同时init后会自动将global_opts中的参数合并到当前module下
var module={
    /* 调用Common.init后是否调用一次查询 */
    default_query:true,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":5,"_selected":[]},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"id",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{  },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{ "show_execute":showExecute,"add_execute":addExecute,"job_state":jobState },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 列表分页查询：GET请求 */
        "page": ctxPath+"/sys/qrtz_job/list",
        /* 删除: DELETE请求 */
        "delete": ctxPath+"/sys/qrtz_job/delete",
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": ctxPath+"/sys/qrtz_job",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"qrtz_job_form",
        actions:{
            // 新增
            "add":{"api":ctxPath+"/sys/qrtz_job/add","enc":"json","method":"POST","title":"新增定时任务::任务配置表","width":"600px"},
            // 修改
            "update":{"api":ctxPath+"/sys/qrtz_job/update","enc":"json","method":"PUT","title":"修改定时任务::任务配置表","width":"600px"},
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            "add":[
                  {name:"application",label:"调度名称/应用名称",type:"select","dict_type":"qrtz_job_app",col:12,attrs:{required:"required"} },
                  {name:"state",label:"任务状态",type:"select","dict_type":"qrtz_job_state_add",col:12,attrs:{required:"required"} },
                  {name:"jobClass",label:"任务全类名",type:"text",col:12,attrs:{required:"required","placeholder":"eg: com.mee.quartz.job.Job01TestService"} },
                  {name:"jobData",label:"任务数据",type:"text",col:12,attrs:{"placeholder":"eg: [] or {} "} },
                  {name:"jobDescription",label:"任务描述",type:"text",col:12 },
                ],
            "update":[
                   {name:"id",type: "hidden",label:"主键"},
                   {name:"application",label:"调度名称/应用名称",type:"text",col:12,attrs:{required:"required",readonly:"readonly"} },
                   {name:"state",label:"任务状态",type:"select","dict_type":"qrtz_job_state",col:12,attrs:{readonly:"readonly",required:"required","style":"pointer-events:none;"} },
                   {name:"jobClass",label:"任务全类名",type:"text",col:12,attrs:{required:"required",readonly:"readonly"} },
                   {name:"jobData",label:"任务数据",type:"text",col:12,attrs:{"placeholder":"eg: [] or {} "} },
                   {name:"jobDescription",label:"任务描述",type:"text",col:12 },
                ],
            }
    },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:[],
    // 上面需要初始化角色在调用接口后会回写到此处
    // .执行中 .暂停 COMPLETE.完成 . .初始化/未启动
    dicts:{ "qrtz_job_state":{
              "EXECUTING":{"v":"EXECUTING","l":"正常执行"},
              "PAUSED":{"v":"PAUSED","l":"停止"},
              "COMPLETE":{"v":"COMPLETE","l":"完成"},
              "ERROR":{"v":"ERROR","l":"异常"},
              "INIT":{"v":"INIT","l":"初始化"}
            },
            "qrtz_job_state_add":{
                          "INIT":{"v":"INIT","l":"初始化"},
                          "EXECUTING":{"v":"EXECUTING","l":"正常执行"},
                        },
            "qrtz_job_app":{}},
};

// 表单参数 这个参数如有表单则必须定义，同时init后会自动将global_opts中的参数合并到当前module下
var module_execute={
    /* 各个区域dom id，这个id是在对应业务html中配置的，如果只有一个查询列表则无需声明以下三个配置字段 */
    // 搜索表单dom id
    search_form_id:"data-search2",
    // table列表dom id
    data_list_id:"data-list2",
    // 模板dom id
    template_id:"template-data-list2",
    /* 调用Common.init后是否调用一次查询 */
    default_query:true,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":10,"_selected":[]},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"id",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{  },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{ "do_add0":doAddAndUpdateBefore,"do_update0":doAddAndUpdateBefore,"execute_state":executeState },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 列表分页查询：GET请求 */
        "page": ctxPath+"/sys/qrtz_execute/list",
        /* 删除: DELETE请求 */
        "delete": ctxPath+"/sys/qrtz_execute/delete",
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": ctxPath+"/sys/qrtz_execute",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"qrtz_execute_form",
        actions:{
            "add":{"api":ctxPath+"/sys/qrtz_execute/add","enc":"json","method":"POST","title":"新增定时任务::执行配置表","width":"600px","show_after_func":showFormAfter},
            // 修改
            "update":{"api":ctxPath+"/sys/qrtz_execute/update","enc":"json","method":"PUT","title":"修改定时任务::执行配置表","width":"600px","show_before_func":showFormBefore,"show_after_func":showFormAfter},
            // 导入
            "import":{"api":app+"/sys/qrtz_execute/import","enc":"form","method":"POST","title":"导入定时任务::执行配置表数据"}
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            "add":[
                  {name:"application",label:"调度名称/应用名称",type:"text",col:12,attrs:{readonly:"readonly"} },
                  {name:"jobClass",label:"任务全类名",type:"text",col:12,attrs:{readonly:"readonly"} },

                  {name:"pid",label:"关联任务(QRTZ_JOB::ID)",type:"hidden",col:6,attrs:{required:"required"} },

                  {name:"jobType",label:"任务类型",type:"select","dict_type":"qrtz_execute_type",col:6,attrs:{required:"required"} },
                  {name:"state",label:"任务状态",type:"select","dict_type":"qrtz_execute_state_add",col:6,attrs:{required:"required"} },
                  {name:"cron",label:"CRON:cron表达式",type:"text",col:6,attrs:{"placeholder":"eg: 0/30 * * * * ?"} },
                  {name:"zoneId",label:"CRON:时区",type:"select","dict_type":"quartz_zones",col:6,title:"可以为空，默认为: Asia/Shanghai " },
                  {name:"repeatCount",label:"SIMPLE:重复/执行次数",type:"number",col:6,title:"=-1时无次数限制",attrs:{"placeholder":"eg: -1"} },
                  {name:"repeatInterval",label:"SIMPLE:执行时间间隔",type:"number",col:6,title:"单位是毫秒,最底应该大于10毫秒",attrs:{"placeholder":"eg: 60000"} },
//                  {name:"time_triggered",label:"SIMPLE:已执行次数",type:"text",col:6,attrs:{required:"required"} },
//                  {name:"prev_fire_time",label:"上一次执行时间",type:"text",col:6 },
//                  {name:"next_fire_time",label:"下一次执行时间",type:"text",col:6,title:"若>0 则此时间为当前循环执行任务的时间" },
                  {name:"_start_time",label:"任务开始时间",type:"datetime-local",col:6,title:"可选，为空则为当前事件+15S" },
                  {name:"_end_time",label:"任务结束时间",type:"datetime-local",col:6,title:"默认-1,-1时没有结束时间" },
                ],
            "update":[
                   {name:"id",type: "hidden",label:"主键"},
                   {name:"pid",label:"关联任务(QRTZ_JOB::ID)",type:"hidden",col:6,attrs:{required:"required",readonly:"readonly"} },
                   {name:"jobType",label:"任务类型",type:"text",col:12,attrs:{required:"required",readonly:"readonly"} },
                   {name:"state",label:"任务状态",type:"select","dict_type":"qrtz_execute_state",col:12,attrs:{readonly:"readonly",required:"required","style":"pointer-events:none;"} },
                   {name:"cron",label:"CRON:cron表达式",type:"text",col:12,attrs:{"placeholder":"eg: 0/30 * * * * ?"} },
                   {name:"zoneId",label:"CRON:时区",type:"select","dict_type":"quartz_zones",col:12 },
                   {name:"repeatCount",label:"SIMPLE:重复/执行次数",type:"number",col:12,title:"=-1时无次数限制",attrs:{"placeholder":"eg: -1"}},
                   {name:"repeatInterval",label:"SIMPLE:执行时间间隔",type:"number",col:12,title:"单位是毫秒，最底应该大于10毫秒",attrs:{"placeholder":"eg: 60000"} },
                   {name:"timeTriggered",type: "hidden",label:"SIMPLE:已执行次数",col:6,attrs:{required:"required"} },
                   {name:"prevFireTime",type: "hidden",label:"上一次执行时间",col:6 },
                   {name:"nextFireTime",type: "hidden",label:"下一次执行时间(当前次)",col:6 },
                   {name:"_start_time",label:"任务开始时间",type:"datetime-local",col:6,title:"可选，为空则为当前事件+15S" },
                   {name:"_end_time",label:"任务结束时间",type:"datetime-local",col:6,title:"默认为-1,-1时没有结束时间" },
                ],
             "import":[
                  {name:"file",label:"文件",col:12,type:"file",attrs:{required:"required",title:"非空,若导入失败请删除excel数据行下方空白行～"}},
                ],
            }
    },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:[],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ "qrtz_execute_state":{
                          "EXECUTING":{"v":"EXECUTING","l":"正常执行"},
                          "PAUSED":{"v":"PAUSED","l":"停止"},
                          "COMPLETE":{"v":"COMPLETE","l":"完成"},
                          "ERROR":{"v":"ERROR","l":"异常"},
                          "INIT":{"v":"INIT","l":"初始化"},
                        },
            "qrtz_execute_state_add":{
                          "INIT":{"v":"INIT","l":"初始化"},
                          "EXECUTING":{"v":"EXECUTING","l":"正常执行"},
                        },
             "qrtz_execute_type":{
                          "CRON":{"v":"CRON","l":"CRON-表达式任务"},
                          "SIMPLE":{"v":"SIMPLE","l":"SIMPLE-简单任务"},
                        },
                        "quartz_zones":quartz_zones
                         },
};

// 初始化
function init(){
    // 先获取字典
    FetchUtils.fetchGet(ctxPath+"/sys/qrtz_job/listApp",{},function(res){
        if( res && 1===res.status ){
          module.dicts.qrtz_job_app=res.data;
          Common.init(module);
          Common2.init(module_execute);
        }else{
          alert(!res?"导入数据失败,请稍后重试(^。^)！":res.msg?res.msg:res);
        }
    });

}

/*
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function showExecute( data,idx,elem ){
    // 选中的着色
    let tr_nodes = elem.parentNode.parentNode.querySelectorAll("tr");
    for( let i=0;i<tr_nodes.length;i++ ){
        if( idx===i ){
            tr_nodes[i].style.backgroundColor="rgb(209, 250, 209)";
            tr_nodes[i].style.textShadow="0 1px 0 rgb(15, 70, 30)";
        }else{
            tr_nodes[i].style.backgroundColor="";
            tr_nodes[i].style.textShadow="";
        }
    }

    let id=data.id;
    let job_class=data.job_class;
    // 更新字典明细 title
    let query_id = module_execute.search_form_id;
    document.querySelector(`#${query_id}`).querySelector("input[name=pid]").value=id;
    // 必须赋值
    // module_detail.search_form.dict_id=data.id;
    // Common2.global_module.search_form.dict_id=data.id;
    Common2.doQuery();
}
/*
  节点添加
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function addExecute( data,idx,elem ){
    let _data = {"pid":data.id,"application":data.application,"jobClass":data.jobClass};
    Common2.toAdd(_data,idx,elem);
}
/*
  节点添加，数提交到后端之前的动作
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      dialog_dom: 当前dialog的dom对象
      action: 当前行为的配置见module::form_struct::actions
      data: 这个是表单参数
*/
function doAddAndUpdateBefore(dialog_dom,action,data){
  /*
  {
    "application": "QUARTZ-SPRINGBOOT",
    "job_class": "com.mee.qrtz.Test02",
    "pid": "2409191404031002",
    "job_type": "CRON",
    "state": "EXECUTING",
    "cron": "* 0/3 * * * ?",
    "zone_id": "Asia/Shanghai",
    "repeat_count": "",
    "repeat_interval": "",
    "start_time": "2024-09-10 04:44:04",
    "end_time": "2024-09-30 05:55:05"
  }
  */
  let _job_type = data.jobType;
  let _start_time = data._start_time; // 新启字段保证不冲突
  let _end_time = data._end_time; // 新启字段保证不冲突
   data.startTime = (_start_time && _start_time.indexOf(" ") != -1) ? new Date(_start_time).getTime() : -1;
   data.endTime = (_end_time && _end_time.indexOf(" ") != -1) ? new Date(_end_time).getTime() : -1;
   if("CRON"===_job_type){
    data.repeatCount=null;
    data.repeatInterval=null;
   }else if("SIMPLE"===_job_type){
    data.cron = null;
    data.zoneId = null;
   }else{
      alert("不支持的任务类型!"+_job_type);
      return false;
   }
   return true;
}
// 表单显示之前的动作
function showFormBefore( type,action,data ){
    if("update"===type){
        let _start_time = data.startTime;
        let _end_time = data.endTime;
        data._start_time= (_start_time &&　_start_time>0) ? new Date(_start_time).Format("yyyy-MM-dd hh:mm:ss") : -1;
        data._end_time= (_end_time &&　_end_time>0) ? new Date(_end_time).Format("yyyy-MM-dd hh:mm:ss") : -1;
    }else if("add"===type){
        // 默认值
        data._start_time= new Date().Format("yyyy-MM-dd hh:mm:ss");
    }else{
       return false;
    }
    return true;
}

/**
* 显示execute表单之后要做的事儿
* type: 表单类型
* action: 表单基本属性配置
* data: 表单数据
* dialog_dom: 表单dom对象
**/
function showFormAfter(type,action,data,dialog_dom){
    // 处理时间格式
    let _job_type = data.jobType;
    if("add"===type ){
        // 注册change事件
        dialog_dom.querySelector("select[name=jobType]").addEventListener('change', function() {
            let _v = this.value;
            if("CRON"===_v){
                dialog_dom.querySelector("input[name=repeatCount]").parentElement.style.display="none";
                dialog_dom.querySelector("input[name=repeatInterval]").parentElement.style.display="none";
                dialog_dom.querySelector("input[name=cron]").parentElement.style.display="";
                dialog_dom.querySelector("select[name=zoneId]").parentElement.parentElement.style.display="";
            }else if("SIMPLE"===_v){
                dialog_dom.querySelector("input[name=repeatCount]").parentElement.style.display="";
                dialog_dom.querySelector("input[name=repeatInterval]").parentElement.style.display="";
                dialog_dom.querySelector("input[name=cron]").parentElement.style.display="none";
                dialog_dom.querySelector("select[name=zoneId]").parentElement.parentElement.style.display="none";
            }else{
                dialog_dom.querySelector("input[name=repeatCount]").parentElement.style.display="";
                dialog_dom.querySelector("input[name=repeatInterval]").parentElement.style.display="";
                dialog_dom.querySelector("input[name=cron]").parentElement.style.display="";
                dialog_dom.querySelector("select[name=zoneId]").parentElement.parentElement.style.display="";
            }
        });
    }else if("update"===type ){
        if( "CRON"===_job_type ){
            dialog_dom.querySelector("input[name=repeatCount]").parentElement.style.display="none";
            dialog_dom.querySelector("input[name=repeatInterval]").parentElement.style.display="none";
        }else{
            dialog_dom.querySelector("input[name=cron]").parentElement.style.display="none";
            dialog_dom.querySelector("select[name=zoneId]").parentElement.parentElement.style.display="none";
        }
    }else{
      return false;
    }
    return true;
}

/*
  节点添加
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function jobState( data,idx,elem ){
    let job_id = data.id;
    let job_class = data.jobClass;
    let state = data.state;
    let state_msg = "";
    let to_state = "";
    // PAUSED,INIT -> EXECUTING
    // EXECUTING,ERROR -> PAUSED
    if( "PAUSED"===state || "INIT"===state || "COMPLETE"===state ){
       to_state = "EXECUTING";
       state_msg = "启动";
    }else if( "EXECUTING"===state || "ERROR"===state ){
      to_state = "PAUSED";
      state_msg = "暂停";
    }else{
      alert("当前状态不可操作！"+state);
      return;
    }
    if (!confirm(`【${state_msg}】 ${job_class} 任务将变更当前job及其下的所有执行项目(不包含已完成的),确定继续?`)){
        return;
    }
    FetchUtils.fetchPutJson(app+`/sys/qrtz_job/state?job_id=${job_id}&state=${to_state}`,{},function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"解锁操作失败");
            return;
        }
        // alert(res.msg);
        Common.doQuery();
        Common2.doQuery();
    });
}
function executeState( data,idx,elem ){
  let execute_id = data.id;
  let state = data.state;
  let state_msg = "";
  let to_state = "";
  // PAUSED,INIT -> EXECUTING
  // EXECUTING,ERROR -> PAUSED
  if( "PAUSED"===state || "INIT"===state ){
     to_state = "EXECUTING";
     state_msg = "启动";
  }else if( "EXECUTING"===state || "ERROR"===state ){
    to_state = "PAUSED";
    state_msg = "暂停";
  }else{
    alert("当前状态不可操作！"+state);
    return;
  }
  if (!confirm(`【${state_msg}】 ${execute_id} 执行项(不包含已完成的),确定继续?`)){
      return;
  }
  FetchUtils.fetchPutJson(app+`/sys/qrtz_execute/state?execute_id=${execute_id}&state=${to_state}`,{},function(res){
      if( !res || res.status!==1 ){
          alert(res?res.msg:"解锁操作失败");
          return;
      }
      // alert(res.msg);
      Common2.doQuery();
  });
}

export { init }
