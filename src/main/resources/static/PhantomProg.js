var page = require('webpage').create();

//var url = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-";	

var url = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-";	
var useragent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36";
var referrer = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-";
var cookie1 = "E2109FAE3F0EA09C38163BBF24DD9A7E~t53LAJFVQDcB/+q14T3amyom/sJ5dm1gV7z2R0E3DKg6WiKBpLgF0t1Mv32gad4CqvL3DIswsfAKTAHD16vNlona86iCn3267hHmZU/O7DrKPY73XE6C4p5geps7yRwXxoUOlsqqPtbPsWsxE7cyDxr6R+RFqYMoDc9XuhS7e18=";
		

page.settings.userAgent = useragent;


page.customHeaders = {
  "Accept-Encoding":"gzip",
  "Accept-Language": "en-IN,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,hi;q=0.6"
  "Referer": referrer
};

page.addCookie = { 
  domain: '.nseindia.com',
  expires: 'Sat Oct 11 2014 21:44:33 GMT+0200 (CEST)',
  expiry: 1476128618,
  httponly: false,
  name: 'bm_sv',
  path: '/',
  secure: true,
  value: cookie1
}



console.log("hello phantom!!");

page.open(url,function(status) {
  console.log("Status: " + status);
  console.log(page.title);
  var content = page.content;
  //console.log('Content: ' + content);
  var title = page.evaluate(function() {
    return document.title;
  });

  console.log(title);

  page.set('onResourceReceived', function(resource){
    console.log(resource.status); 
});
 
  phantom.exit();
});

