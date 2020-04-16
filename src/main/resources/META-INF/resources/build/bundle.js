var app=function(){"use strict";function e(){}function t(e){return e()}function n(){return Object.create(null)}function r(e){e.forEach(t)}function o(e){return"function"==typeof e}function s(e,t){return e!=e?t==t:e!==t||e&&"object"==typeof e||"function"==typeof e}function c(t,...n){if(null==t)return e;const r=t.subscribe(...n);return r.unsubscribe?()=>r.unsubscribe():r}function i(e){let t;return c(e,e=>t=e)(),t}function a(e,t,n){e.$$.on_destroy.push(c(t,n))}function u(e,t){e.appendChild(t)}function l(e,t,n){e.insertBefore(t,n||null)}function f(e){e.parentNode.removeChild(e)}function d(e,t){for(let n=0;n<e.length;n+=1)e[n]&&e[n].d(t)}function p(e){return document.createElement(e)}function h(e){return document.createTextNode(e)}function m(){return h(" ")}function g(){return h("")}function v(e,t,n,r){return e.addEventListener(t,n,r),()=>e.removeEventListener(t,n,r)}function w(e,t,n){null==n?e.removeAttribute(t):e.getAttribute(t)!==n&&e.setAttribute(t,n)}function y(e,t){t=""+t,e.data!==t&&(e.data=t)}function b(e,t){(null!=t||e.value)&&(e.value=t)}function x(e,t,n){e.classList[n?"add":"remove"](t)}let $;function k(e){$=e}function C(){if(!$)throw new Error("Function called outside component initialization");return $}function S(e){C().$$.on_destroy.push(e)}const E=[],N=[],O=[],R=[],A=Promise.resolve();let T=!1;function j(e){O.push(e)}let L=!1;const q=new Set;function B(){if(!L){L=!0;do{for(let e=0;e<E.length;e+=1){const t=E[e];k(t),P(t.$$)}for(E.length=0;N.length;)N.pop()();for(let e=0;e<O.length;e+=1){const t=O[e];q.has(t)||(q.add(t),t())}O.length=0}while(E.length);for(;R.length;)R.pop()();T=!1,L=!1,q.clear()}}function P(e){if(null!==e.fragment){e.update(),r(e.before_update);const t=e.dirty;e.dirty=[-1],e.fragment&&e.fragment.p(e.ctx,t),e.after_update.forEach(j)}}const U=new Set;let _;function D(e,t){e&&e.i&&(U.delete(e),e.i(t))}function F(e,t,n,r){if(e&&e.o){if(U.has(e))return;U.add(e),_.c.push(()=>{U.delete(e),r&&(n&&e.d(1),r())}),e.o(t)}}function I(e){e&&e.c()}function M(e,n,s){const{fragment:c,on_mount:i,on_destroy:a,after_update:u}=e.$$;c&&c.m(n,s),j(()=>{const n=i.map(t).filter(o);a?a.push(...n):r(n),e.$$.on_mount=[]}),u.forEach(j)}function J(e,t){const n=e.$$;null!==n.fragment&&(r(n.on_destroy),n.fragment&&n.fragment.d(t),n.on_destroy=n.fragment=null,n.ctx=[])}function H(e,t){-1===e.$$.dirty[0]&&(E.push(e),T||(T=!0,A.then(B)),e.$$.dirty.fill(0)),e.$$.dirty[t/31|0]|=1<<t%31}function z(t,o,s,c,i,a,u=[-1]){const l=$;k(t);const d=o.props||{},p=t.$$={fragment:null,ctx:null,props:a,update:e,not_equal:i,bound:n(),on_mount:[],on_destroy:[],before_update:[],after_update:[],context:new Map(l?l.$$.context:[]),callbacks:n(),dirty:u};let h=!1;if(p.ctx=s?s(t,d,(e,n,...r)=>{const o=r.length?r[0]:n;return p.ctx&&i(p.ctx[e],p.ctx[e]=o)&&(p.bound[e]&&p.bound[e](o),h&&H(t,e)),n}):[],p.update(),h=!0,r(p.before_update),p.fragment=!!c&&c(p.ctx),o.target){if(o.hydrate){const e=function(e){return Array.from(e.childNodes)}(o.target);p.fragment&&p.fragment.l(e),e.forEach(f)}else p.fragment&&p.fragment.c();o.intro&&D(t.$$.fragment),M(t,o.target,o.anchor),B()}k(l)}class Y{$destroy(){J(this,1),this.$destroy=e}$on(e,t){const n=this.$$.callbacks[e]||(this.$$.callbacks[e]=[]);return n.push(t),()=>{const e=n.indexOf(t);-1!==e&&n.splice(e,1)}}$set(){}}var W=function(e,t){return function(){for(var n=new Array(arguments.length),r=0;r<n.length;r++)n[r]=arguments[r];return e.apply(t,n)}},X=Object.prototype.toString;function V(e){return"[object Array]"===X.call(e)}function G(e){return void 0===e}function K(e){return null!==e&&"object"==typeof e}function Q(e){return"[object Function]"===X.call(e)}function Z(e,t){if(null!=e)if("object"!=typeof e&&(e=[e]),V(e))for(var n=0,r=e.length;n<r;n++)t.call(null,e[n],n,e);else for(var o in e)Object.prototype.hasOwnProperty.call(e,o)&&t.call(null,e[o],o,e)}var ee={isArray:V,isArrayBuffer:function(e){return"[object ArrayBuffer]"===X.call(e)},isBuffer:function(e){return null!==e&&!G(e)&&null!==e.constructor&&!G(e.constructor)&&"function"==typeof e.constructor.isBuffer&&e.constructor.isBuffer(e)},isFormData:function(e){return"undefined"!=typeof FormData&&e instanceof FormData},isArrayBufferView:function(e){return"undefined"!=typeof ArrayBuffer&&ArrayBuffer.isView?ArrayBuffer.isView(e):e&&e.buffer&&e.buffer instanceof ArrayBuffer},isString:function(e){return"string"==typeof e},isNumber:function(e){return"number"==typeof e},isObject:K,isUndefined:G,isDate:function(e){return"[object Date]"===X.call(e)},isFile:function(e){return"[object File]"===X.call(e)},isBlob:function(e){return"[object Blob]"===X.call(e)},isFunction:Q,isStream:function(e){return K(e)&&Q(e.pipe)},isURLSearchParams:function(e){return"undefined"!=typeof URLSearchParams&&e instanceof URLSearchParams},isStandardBrowserEnv:function(){return("undefined"==typeof navigator||"ReactNative"!==navigator.product&&"NativeScript"!==navigator.product&&"NS"!==navigator.product)&&("undefined"!=typeof window&&"undefined"!=typeof document)},forEach:Z,merge:function e(){var t={};function n(n,r){"object"==typeof t[r]&&"object"==typeof n?t[r]=e(t[r],n):t[r]=n}for(var r=0,o=arguments.length;r<o;r++)Z(arguments[r],n);return t},deepMerge:function e(){var t={};function n(n,r){"object"==typeof t[r]&&"object"==typeof n?t[r]=e(t[r],n):t[r]="object"==typeof n?e({},n):n}for(var r=0,o=arguments.length;r<o;r++)Z(arguments[r],n);return t},extend:function(e,t,n){return Z(t,(function(t,r){e[r]=n&&"function"==typeof t?W(t,n):t})),e},trim:function(e){return e.replace(/^\s*/,"").replace(/\s*$/,"")}};function te(e){return encodeURIComponent(e).replace(/%40/gi,"@").replace(/%3A/gi,":").replace(/%24/g,"$").replace(/%2C/gi,",").replace(/%20/g,"+").replace(/%5B/gi,"[").replace(/%5D/gi,"]")}var ne=function(e,t,n){if(!t)return e;var r;if(n)r=n(t);else if(ee.isURLSearchParams(t))r=t.toString();else{var o=[];ee.forEach(t,(function(e,t){null!=e&&(ee.isArray(e)?t+="[]":e=[e],ee.forEach(e,(function(e){ee.isDate(e)?e=e.toISOString():ee.isObject(e)&&(e=JSON.stringify(e)),o.push(te(t)+"="+te(e))})))})),r=o.join("&")}if(r){var s=e.indexOf("#");-1!==s&&(e=e.slice(0,s)),e+=(-1===e.indexOf("?")?"?":"&")+r}return e};function re(){this.handlers=[]}re.prototype.use=function(e,t){return this.handlers.push({fulfilled:e,rejected:t}),this.handlers.length-1},re.prototype.eject=function(e){this.handlers[e]&&(this.handlers[e]=null)},re.prototype.forEach=function(e){ee.forEach(this.handlers,(function(t){null!==t&&e(t)}))};var oe=re,se=function(e,t,n){return ee.forEach(n,(function(n){e=n(e,t)})),e},ce=function(e){return!(!e||!e.__CANCEL__)},ie=function(e,t){ee.forEach(e,(function(n,r){r!==t&&r.toUpperCase()===t.toUpperCase()&&(e[t]=n,delete e[r])}))},ae=function(e,t,n,r,o){return function(e,t,n,r,o){return e.config=t,n&&(e.code=n),e.request=r,e.response=o,e.isAxiosError=!0,e.toJSON=function(){return{message:this.message,name:this.name,description:this.description,number:this.number,fileName:this.fileName,lineNumber:this.lineNumber,columnNumber:this.columnNumber,stack:this.stack,config:this.config,code:this.code}},e}(new Error(e),t,n,r,o)},ue=["age","authorization","content-length","content-type","etag","expires","from","host","if-modified-since","if-unmodified-since","last-modified","location","max-forwards","proxy-authorization","referer","retry-after","user-agent"],le=ee.isStandardBrowserEnv()?function(){var e,t=/(msie|trident)/i.test(navigator.userAgent),n=document.createElement("a");function r(e){var r=e;return t&&(n.setAttribute("href",r),r=n.href),n.setAttribute("href",r),{href:n.href,protocol:n.protocol?n.protocol.replace(/:$/,""):"",host:n.host,search:n.search?n.search.replace(/^\?/,""):"",hash:n.hash?n.hash.replace(/^#/,""):"",hostname:n.hostname,port:n.port,pathname:"/"===n.pathname.charAt(0)?n.pathname:"/"+n.pathname}}return e=r(window.location.href),function(t){var n=ee.isString(t)?r(t):t;return n.protocol===e.protocol&&n.host===e.host}}():function(){return!0},fe=ee.isStandardBrowserEnv()?{write:function(e,t,n,r,o,s){var c=[];c.push(e+"="+encodeURIComponent(t)),ee.isNumber(n)&&c.push("expires="+new Date(n).toGMTString()),ee.isString(r)&&c.push("path="+r),ee.isString(o)&&c.push("domain="+o),!0===s&&c.push("secure"),document.cookie=c.join("; ")},read:function(e){var t=document.cookie.match(new RegExp("(^|;\\s*)("+e+")=([^;]*)"));return t?decodeURIComponent(t[3]):null},remove:function(e){this.write(e,"",Date.now()-864e5)}}:{write:function(){},read:function(){return null},remove:function(){}},de=function(e){return new Promise((function(t,n){var r=e.data,o=e.headers;ee.isFormData(r)&&delete o["Content-Type"];var s=new XMLHttpRequest;if(e.auth){var c=e.auth.username||"",i=e.auth.password||"";o.Authorization="Basic "+btoa(c+":"+i)}var a,u,l=(a=e.baseURL,u=e.url,a&&!/^([a-z][a-z\d\+\-\.]*:)?\/\//i.test(u)?function(e,t){return t?e.replace(/\/+$/,"")+"/"+t.replace(/^\/+/,""):e}(a,u):u);if(s.open(e.method.toUpperCase(),ne(l,e.params,e.paramsSerializer),!0),s.timeout=e.timeout,s.onreadystatechange=function(){if(s&&4===s.readyState&&(0!==s.status||s.responseURL&&0===s.responseURL.indexOf("file:"))){var r,o,c,i,a,u="getAllResponseHeaders"in s?(r=s.getAllResponseHeaders(),a={},r?(ee.forEach(r.split("\n"),(function(e){if(i=e.indexOf(":"),o=ee.trim(e.substr(0,i)).toLowerCase(),c=ee.trim(e.substr(i+1)),o){if(a[o]&&ue.indexOf(o)>=0)return;a[o]="set-cookie"===o?(a[o]?a[o]:[]).concat([c]):a[o]?a[o]+", "+c:c}})),a):a):null,l={data:e.responseType&&"text"!==e.responseType?s.response:s.responseText,status:s.status,statusText:s.statusText,headers:u,config:e,request:s};!function(e,t,n){var r=n.config.validateStatus;!r||r(n.status)?e(n):t(ae("Request failed with status code "+n.status,n.config,null,n.request,n))}(t,n,l),s=null}},s.onabort=function(){s&&(n(ae("Request aborted",e,"ECONNABORTED",s)),s=null)},s.onerror=function(){n(ae("Network Error",e,null,s)),s=null},s.ontimeout=function(){var t="timeout of "+e.timeout+"ms exceeded";e.timeoutErrorMessage&&(t=e.timeoutErrorMessage),n(ae(t,e,"ECONNABORTED",s)),s=null},ee.isStandardBrowserEnv()){var f=fe,d=(e.withCredentials||le(l))&&e.xsrfCookieName?f.read(e.xsrfCookieName):void 0;d&&(o[e.xsrfHeaderName]=d)}if("setRequestHeader"in s&&ee.forEach(o,(function(e,t){void 0===r&&"content-type"===t.toLowerCase()?delete o[t]:s.setRequestHeader(t,e)})),ee.isUndefined(e.withCredentials)||(s.withCredentials=!!e.withCredentials),e.responseType)try{s.responseType=e.responseType}catch(t){if("json"!==e.responseType)throw t}"function"==typeof e.onDownloadProgress&&s.addEventListener("progress",e.onDownloadProgress),"function"==typeof e.onUploadProgress&&s.upload&&s.upload.addEventListener("progress",e.onUploadProgress),e.cancelToken&&e.cancelToken.promise.then((function(e){s&&(s.abort(),n(e),s=null)})),void 0===r&&(r=null),s.send(r)}))},pe={"Content-Type":"application/x-www-form-urlencoded"};function he(e,t){!ee.isUndefined(e)&&ee.isUndefined(e["Content-Type"])&&(e["Content-Type"]=t)}var me,ge={adapter:(("undefined"!=typeof XMLHttpRequest||void 0!=={env:{isProd:!0}}&&'[object {"env":{"isProd":true}}]'===Object.prototype.toString.call({env:{isProd:!0}}))&&(me=de),me),transformRequest:[function(e,t){return ie(t,"Accept"),ie(t,"Content-Type"),ee.isFormData(e)||ee.isArrayBuffer(e)||ee.isBuffer(e)||ee.isStream(e)||ee.isFile(e)||ee.isBlob(e)?e:ee.isArrayBufferView(e)?e.buffer:ee.isURLSearchParams(e)?(he(t,"application/x-www-form-urlencoded;charset=utf-8"),e.toString()):ee.isObject(e)?(he(t,"application/json;charset=utf-8"),JSON.stringify(e)):e}],transformResponse:[function(e){if("string"==typeof e)try{e=JSON.parse(e)}catch(e){}return e}],timeout:0,xsrfCookieName:"XSRF-TOKEN",xsrfHeaderName:"X-XSRF-TOKEN",maxContentLength:-1,validateStatus:function(e){return e>=200&&e<300}};ge.headers={common:{Accept:"application/json, text/plain, */*"}},ee.forEach(["delete","get","head"],(function(e){ge.headers[e]={}})),ee.forEach(["post","put","patch"],(function(e){ge.headers[e]=ee.merge(pe)}));var ve=ge;function we(e){e.cancelToken&&e.cancelToken.throwIfRequested()}var ye=function(e){return we(e),e.headers=e.headers||{},e.data=se(e.data,e.headers,e.transformRequest),e.headers=ee.merge(e.headers.common||{},e.headers[e.method]||{},e.headers),ee.forEach(["delete","get","head","post","put","patch","common"],(function(t){delete e.headers[t]})),(e.adapter||ve.adapter)(e).then((function(t){return we(e),t.data=se(t.data,t.headers,e.transformResponse),t}),(function(t){return ce(t)||(we(e),t&&t.response&&(t.response.data=se(t.response.data,t.response.headers,e.transformResponse))),Promise.reject(t)}))},be=function(e,t){t=t||{};var n={},r=["url","method","params","data"],o=["headers","auth","proxy"],s=["baseURL","url","transformRequest","transformResponse","paramsSerializer","timeout","withCredentials","adapter","responseType","xsrfCookieName","xsrfHeaderName","onUploadProgress","onDownloadProgress","maxContentLength","validateStatus","maxRedirects","httpAgent","httpsAgent","cancelToken","socketPath"];ee.forEach(r,(function(e){void 0!==t[e]&&(n[e]=t[e])})),ee.forEach(o,(function(r){ee.isObject(t[r])?n[r]=ee.deepMerge(e[r],t[r]):void 0!==t[r]?n[r]=t[r]:ee.isObject(e[r])?n[r]=ee.deepMerge(e[r]):void 0!==e[r]&&(n[r]=e[r])})),ee.forEach(s,(function(r){void 0!==t[r]?n[r]=t[r]:void 0!==e[r]&&(n[r]=e[r])}));var c=r.concat(o).concat(s),i=Object.keys(t).filter((function(e){return-1===c.indexOf(e)}));return ee.forEach(i,(function(r){void 0!==t[r]?n[r]=t[r]:void 0!==e[r]&&(n[r]=e[r])})),n};function xe(e){this.defaults=e,this.interceptors={request:new oe,response:new oe}}xe.prototype.request=function(e){"string"==typeof e?(e=arguments[1]||{}).url=arguments[0]:e=e||{},(e=be(this.defaults,e)).method?e.method=e.method.toLowerCase():this.defaults.method?e.method=this.defaults.method.toLowerCase():e.method="get";var t=[ye,void 0],n=Promise.resolve(e);for(this.interceptors.request.forEach((function(e){t.unshift(e.fulfilled,e.rejected)})),this.interceptors.response.forEach((function(e){t.push(e.fulfilled,e.rejected)}));t.length;)n=n.then(t.shift(),t.shift());return n},xe.prototype.getUri=function(e){return e=be(this.defaults,e),ne(e.url,e.params,e.paramsSerializer).replace(/^\?/,"")},ee.forEach(["delete","get","head","options"],(function(e){xe.prototype[e]=function(t,n){return this.request(ee.merge(n||{},{method:e,url:t}))}})),ee.forEach(["post","put","patch"],(function(e){xe.prototype[e]=function(t,n,r){return this.request(ee.merge(r||{},{method:e,url:t,data:n}))}}));var $e=xe;function ke(e){this.message=e}ke.prototype.toString=function(){return"Cancel"+(this.message?": "+this.message:"")},ke.prototype.__CANCEL__=!0;var Ce=ke;function Se(e){if("function"!=typeof e)throw new TypeError("executor must be a function.");var t;this.promise=new Promise((function(e){t=e}));var n=this;e((function(e){n.reason||(n.reason=new Ce(e),t(n.reason))}))}Se.prototype.throwIfRequested=function(){if(this.reason)throw this.reason},Se.source=function(){var e;return{token:new Se((function(t){e=t})),cancel:e}};var Ee=Se;function Ne(e){var t=new $e(e),n=W($e.prototype.request,t);return ee.extend(n,$e.prototype,t),ee.extend(n,t),n}var Oe=Ne(ve);Oe.Axios=$e,Oe.create=function(e){return Ne(be(Oe.defaults,e))},Oe.Cancel=Ce,Oe.CancelToken=Ee,Oe.isCancel=ce,Oe.all=function(e){return Promise.all(e)},Oe.spread=function(e){return function(t){return e.apply(null,t)}};var Re=Oe,Ae=Oe;Re.default=Ae;var Te=Re;let je="http://localhost:8080",Le="ws://localhost:8080";async function qe(){return Te.get(je+"/api/player/new")}async function Be(e){return Te.get(je+"/api/player/"+e)}function Pe(e,t,n){const r=e.slice();return r[1]=t[n],r[3]=n,r}function Ue(e){let t,n,r,o=e[0],s=[];for(let t=0;t<o.length;t+=1)s[t]=De(Pe(e,o,t));return{c(){t=p("table"),n=p("tr"),n.innerHTML='<th class="svelte-crwkr7">Level</th> \n        <th class="svelte-crwkr7">Name</th> \n        <th class="svelte-crwkr7">Wins</th> \n        <th class="svelte-crwkr7">Losses</th> \n        <th class="svelte-crwkr7">Ties</th> \n        <th class="svelte-crwkr7">Games</th> \n        <th class="svelte-crwkr7">Win%</th> \n        <th class="svelte-crwkr7">MMR</th>',r=m();for(let e=0;e<s.length;e+=1)s[e].c();w(n,"class","svelte-crwkr7"),w(t,"class","leaderboard svelte-crwkr7")},m(e,o){l(e,t,o),u(t,n),u(t,r);for(let e=0;e<s.length;e+=1)s[e].m(t,null)},p(e,n){if(1&n){let r;for(o=e[0],r=0;r<o.length;r+=1){const c=Pe(e,o,r);s[r]?s[r].p(c,n):(s[r]=De(c),s[r].c(),s[r].m(t,null))}for(;r<s.length;r+=1)s[r].d(1);s.length=o.length}},d(e){e&&f(t),d(s,e)}}}function _e(t){let n;return{c(){n=p("p"),n.textContent="Loading leaderboard.."},m(e,t){l(e,n,t)},p:e,d(e){e&&f(n)}}}function De(e){let t,n,r,o,s,c,i,a,d,g,v,b,x,$,k,C,S,E,N,O,R,A,T,j,L,q,B,P=e[1].level+"",U=e[1].name+"",_=e[1].wins+"",D=e[1].losses+"",F=e[1].ties+"",I=e[1].wins+e[1].losses+e[1].ties+"",M=Ie(e[1])+"",J=e[1].experience+"";return{c(){var u;t=p("tr"),n=p("td"),r=h(P),s=m(),c=p("td"),i=h(U),a=m(),d=p("td"),g=h(_),v=m(),b=p("td"),x=h(D),$=m(),k=p("td"),C=h(F),S=m(),E=p("td"),N=h(I),O=m(),R=p("td"),A=h(M),T=h("%"),j=m(),L=p("td"),q=h(J),B=m(),w(n,"class",(u=0===e[3]?"goldentext":"bold",o=(null==u?"":u)+" svelte-crwkr7")),w(c,"class","bold svelte-crwkr7"),w(d,"class","greentext svelte-crwkr7"),w(b,"class","redtext svelte-crwkr7"),w(k,"class","redtext svelte-crwkr7"),w(E,"class","bold svelte-crwkr7"),w(R,"class","greentext svelte-crwkr7"),w(L,"class","bold svelte-crwkr7"),w(t,"class","svelte-crwkr7")},m(e,o){l(e,t,o),u(t,n),u(n,r),u(t,s),u(t,c),u(c,i),u(t,a),u(t,d),u(d,g),u(t,v),u(t,b),u(b,x),u(t,$),u(t,k),u(k,C),u(t,S),u(t,E),u(E,N),u(t,O),u(t,R),u(R,A),u(R,T),u(t,j),u(t,L),u(L,q),u(t,B)},p(e,t){1&t&&P!==(P=e[1].level+"")&&y(r,P),1&t&&U!==(U=e[1].name+"")&&y(i,U),1&t&&_!==(_=e[1].wins+"")&&y(g,_),1&t&&D!==(D=e[1].losses+"")&&y(x,D),1&t&&F!==(F=e[1].ties+"")&&y(C,F),1&t&&I!==(I=e[1].wins+e[1].losses+e[1].ties+"")&&y(N,I),1&t&&M!==(M=Ie(e[1])+"")&&y(A,M),1&t&&J!==(J=e[1].experience+"")&&y(q,J)},d(e){e&&f(t)}}}function Fe(t){let n;function r(e,t){return e[0]?Ue:_e}let o=r(t),s=o(t);return{c(){n=p("main"),s.c(),w(n,"class","svelte-crwkr7")},m(e,t){l(e,n,t),s.m(n,null)},p(e,[t]){o===(o=r(e))&&s?s.p(e,t):(s.d(1),s=o(e),s&&(s.c(),s.m(n,null)))},i:e,o:e,d(e){e&&f(n),s.d()}}}function Ie(e){const t=function(e){return Math.trunc(e.wins/(e.wins+e.losses+e.ties)*100)}(e);return isNaN(t)?0:t}async function Me(){return async function(){return Te.get(je+"/api/player")}()}function Je(e,t,n){let r;return setInterval(async()=>{let e=await Me();n(0,r=e.data)},1e3),[r]}je="https://fivexo.herokuapp.com",Le="wss://fivexo.herokuapp.com";class He extends Y{constructor(e){super(),z(this,e,Je,Fe,s,{})}}const ze=[];function Ye(t,n=e){let r;const o=[];function c(e){if(s(t,e)&&(t=e,r)){const e=!ze.length;for(let e=0;e<o.length;e+=1){const n=o[e];n[1](),ze.push(n,t)}if(e){for(let e=0;e<ze.length;e+=2)ze[e][0](ze[e+1]);ze.length=0}}}return{set:c,update:function(e){c(e(t))},subscribe:function(s,i=e){const a=[s,i];return o.push(a),1===o.length&&(r=n(c)||e),s(t),()=>{const e=o.indexOf(a);-1!==e&&o.splice(e,1),0===o.length&&(r(),r=null)}}}}const We=Ye(0),Xe=Ye(),Ve=Ye(),Ge=Ye(),Ke=Ye();function Qe(e,t,n){const r=e.slice();return r[17]=t[n],r[19]=n,r}function Ze(e,t,n){const r=e.slice();return r[14]=t[n],r[16]=n,r}function et(e){let t,n,r,o,s=e[0][e[16]][e[19]]+"";function c(...t){return e[13](e[16],e[19],...t)}return{c(){t=p("button"),n=h(s),t.disabled=r=!e[5](e[1],e[2],e[3]),w(t,"class","svelte-tsu46q"),x(t,"enabled",e[5](e[1],e[2],e[3]))},m(e,r,s){l(e,t,r),u(t,n),s&&o(),o=v(t,"click",c)},p(o,c){e=o,1&c&&s!==(s=e[0][e[16]][e[19]]+"")&&y(n,s),14&c&&r!==(r=!e[5](e[1],e[2],e[3]))&&(t.disabled=r),46&c&&x(t,"enabled",e[5](e[1],e[2],e[3]))},d(e){e&&f(t),o()}}}function tt(e){let t,n,r=e[14],o=[];for(let t=0;t<r.length;t+=1)o[t]=et(Qe(e,r,t));return{c(){for(let e=0;e<o.length;e+=1)o[e].c();t=m(),n=p("br")},m(e,r){for(let t=0;t<o.length;t+=1)o[t].m(e,r);l(e,t,r),l(e,n,r)},p(e,n){if(111&n){let s;for(r=e[14],s=0;s<r.length;s+=1){const c=Qe(e,r,s);o[s]?o[s].p(c,n):(o[s]=et(c),o[s].c(),o[s].m(t.parentNode,t))}for(;s<o.length;s+=1)o[s].d(1);o.length=r.length}},d(e){d(o,e),e&&f(t),e&&f(n)}}}function nt(e){let t,n,r=e[4]===e[1]?"You win!":"You lose!";return{c(){t=p("p"),n=h(r)},m(e,r){l(e,t,r),u(t,n)},p(e,t){18&t&&r!==(r=e[4]===e[1]?"You win!":"You lose!")&&y(n,r)},d(e){e&&f(t)}}}function rt(e){let t,n=!!e[1]&&!!e[2]&&ot(e);return{c(){n&&n.c(),t=g()},m(e,r){n&&n.m(e,r),l(e,t,r)},p(e,r){e[1]&&e[2]?n?n.p(e,r):(n=ot(e),n.c(),n.m(t.parentNode,t)):n&&(n.d(1),n=null)},d(e){n&&n.d(e),e&&f(t)}}}function ot(e){let t,n,r,o;function s(e,t){return e[1]===e[2]?ct:st}let c=s(e),i=c(e);return{c(){i.c(),t=m(),n=p("p"),r=h("You: "),o=h(e[1])},m(e,s){i.m(e,s),l(e,t,s),l(e,n,s),u(n,r),u(n,o)},p(e,n){c!==(c=s(e))&&(i.d(1),i=c(e),i&&(i.c(),i.m(t.parentNode,t))),2&n&&y(o,e[1])},d(e){i.d(e),e&&f(t),e&&f(n)}}}function st(e){let t;return{c(){t=p("p"),t.textContent="Opponents' turn"},m(e,n){l(e,t,n)},d(e){e&&f(t)}}}function ct(e){let t;return{c(){t=p("p"),t.textContent="Your turn"},m(e,n){l(e,t,n)},d(e){e&&f(t)}}}function it(t){let n,r,o,s,c=t[0],i=[];for(let e=0;e<c.length;e+=1)i[e]=tt(Ze(t,c,e));function a(e,t){return e[3]?nt:rt}let h=a(t),g=h(t);return{c(){n=p("main"),r=p("h1"),r.textContent="Five In A Row",o=m();for(let e=0;e<i.length;e+=1)i[e].c();s=m(),g.c(),w(n,"class","svelte-tsu46q")},m(e,t){l(e,n,t),u(n,r),u(n,o);for(let e=0;e<i.length;e+=1)i[e].m(n,null);u(n,s),g.m(n,null)},p(e,[t]){if(111&t){let r;for(c=e[0],r=0;r<c.length;r+=1){const o=Ze(e,c,r);i[r]?i[r].p(o,t):(i[r]=tt(o),i[r].c(),i[r].m(n,s))}for(;r<i.length;r+=1)i[r].d(1);i.length=c.length}h===(h=a(e))&&g?g.p(e,t):(g.d(1),g=h(e),g&&(g.c(),g.m(n,null)))},i:e,o:e,d(e){e&&f(n),d(i,e),g.d()}}}let at,ut;function lt(e,t,n){let r,o,s;a(e,Ge,e=>n(7,r=e)),a(e,Ke,e=>n(8,o=e));let c,i=!1,u=!1,l=null;const f=Ke.subscribe(e=>{e&&(n(1,c=e.character),n(2,i=e.turn))}),d=Xe.subscribe(e=>{e&&(n(0,s=e.grid),n(3,u=e.gameOver),u&&n(4,l=e.winnerCharacter))}),p=(e,t,n)=>h(e,t)&&!n,h=(e,t)=>!!e&&!!t&&t==e;function m(){n(0,s=[...Array(15)].map(e=>Array(15).fill(""))),n(1,c=!1),n(2,i=!1),n(3,u=!1)}function g(e,t,c,i,a){p(e,t,c)&&""===s[i][a]&&(r.send(i+" "+a),n(0,s[i][a]=o.character,s),console.log(i+" "+a))}S(f),m();return[s,c,i,u,l,p,g,r,o,f,d,h,m,(e,t)=>g(c,i,u,e,t)]}class ft extends Y{constructor(e){super(),z(this,e,lt,it,s,{})}}function dt(e){let t,n,r,o,s,c,i,a,d,b,x,$,k,C,S,E,N,O,R,A,T,j,L,q,B,P,U,_,D,F,I,M,J,H,z,Y,W,X,V,G,K,Q,Z,ee,te,ne,re,oe,se,ce,ie=e[0].name+"",ae=e[0].id+"",ue=e[0].level+"",le=e[0].wins+"",fe=e[0].losses+"",de=e[0].ties+"",pe=e[0].experience+"",he=e[2]&&pt(e);return{c(){t=p("p"),t.textContent="You",n=m(),r=p("table"),o=p("tr"),s=p("td"),s.textContent="Name",c=m(),i=p("td"),a=h(ie),d=m(),b=p("tr"),x=p("td"),x.textContent="ID",$=m(),k=p("td"),C=h(ae),S=m(),E=p("tr"),N=p("td"),N.textContent="Level",O=m(),R=p("td"),A=h(ue),T=m(),j=p("tr"),L=p("td"),L.textContent="Wins",q=m(),B=p("td"),P=h(le),U=m(),_=p("tr"),D=p("td"),D.textContent="Losses",F=m(),I=p("td"),M=h(fe),J=m(),H=p("tr"),z=p("td"),z.textContent="Ties",Y=m(),W=p("td"),X=h(de),V=m(),G=p("tr"),K=p("td"),K.textContent="MMR",Q=m(),Z=p("td"),ee=h(pe),te=m(),ne=p("p"),re=p("button"),re.textContent="Clicky",oe=m(),he&&he.c(),se=g(),w(t,"class","svelte-1r53d96"),w(s,"class","bold svelte-1r53d96"),w(i,"class","svelte-1r53d96"),w(o,"class","svelte-1r53d96"),w(x,"class","bold svelte-1r53d96"),w(k,"class","svelte-1r53d96"),w(b,"class","svelte-1r53d96"),w(N,"class","bold svelte-1r53d96"),w(R,"class","svelte-1r53d96"),w(E,"class","svelte-1r53d96"),w(L,"class","bold svelte-1r53d96"),w(B,"class","svelte-1r53d96"),w(j,"class","svelte-1r53d96"),w(D,"class","bold svelte-1r53d96"),w(I,"class","svelte-1r53d96"),w(_,"class","svelte-1r53d96"),w(z,"class","bold svelte-1r53d96"),w(W,"class","svelte-1r53d96"),w(H,"class","svelte-1r53d96"),w(K,"class","bold svelte-1r53d96"),w(Z,"class","svelte-1r53d96"),w(G,"class","svelte-1r53d96"),w(r,"class","svelte-1r53d96"),w(re,"class","link svelte-1r53d96"),w(ne,"class","svelte-1r53d96")},m(f,p,h){l(f,t,p),l(f,n,p),l(f,r,p),u(r,o),u(o,s),u(o,c),u(o,i),u(i,a),u(r,d),u(r,b),u(b,x),u(b,$),u(b,k),u(k,C),u(r,S),u(r,E),u(E,N),u(E,O),u(E,R),u(R,A),u(r,T),u(r,j),u(j,L),u(j,q),u(j,B),u(B,P),u(r,U),u(r,_),u(_,D),u(_,F),u(_,I),u(I,M),u(r,J),u(r,H),u(H,z),u(H,Y),u(H,W),u(W,X),u(r,V),u(r,G),u(G,K),u(G,Q),u(G,Z),u(Z,ee),l(f,te,p),l(f,ne,p),u(ne,re),l(f,oe,p),he&&he.m(f,p),l(f,se,p),h&&ce(),ce=v(re,"click",e[5])},p(e,t){1&t&&ie!==(ie=e[0].name+"")&&y(a,ie),1&t&&ae!==(ae=e[0].id+"")&&y(C,ae),1&t&&ue!==(ue=e[0].level+"")&&y(A,ue),1&t&&le!==(le=e[0].wins+"")&&y(P,le),1&t&&fe!==(fe=e[0].losses+"")&&y(M,fe),1&t&&de!==(de=e[0].ties+"")&&y(X,de),1&t&&pe!==(pe=e[0].experience+"")&&y(ee,pe),e[2]?he?he.p(e,t):(he=pt(e),he.c(),he.m(se.parentNode,se)):he&&(he.d(1),he=null)},d(e){e&&f(t),e&&f(n),e&&f(r),e&&f(te),e&&f(ne),e&&f(oe),he&&he.d(e),e&&f(se),ce()}}}function pt(e){let t,n,o,s,c,i,a,d;return{c(){t=p("p"),n=h("Name\r\n          "),o=p("input"),s=m(),c=p("button"),c.textContent="Save",i=m(),a=p("button"),a.textContent="Reset ID",w(t,"class","svelte-1r53d96")},m(f,p,h){l(f,t,p),u(t,n),u(t,o),b(o,e[1]),l(f,s,p),l(f,c,p),l(f,i,p),l(f,a,p),h&&r(d),d=[v(o,"input",e[6]),v(c,"click",e[7]),v(a,"click",mt)]},p(e,t){2&t&&o.value!==e[1]&&b(o,e[1])},d(e){e&&f(t),e&&f(s),e&&f(c),e&&f(i),e&&f(a),r(d)}}}function ht(t){let n,r,o=t[0]&&dt(t);return{c(){n=p("gui"),r=p("div"),o&&o.c(),w(r,"class","wrapper svelte-1r53d96")},m(e,t){l(e,n,t),u(n,r),o&&o.m(r,null)},p(e,[t]){e[0]?o?o.p(e,t):(o=dt(e),o.c(),o.m(r,null)):o&&(o.d(1),o=null)},i:e,o:e,d(e){e&&f(n),o&&o.d()}}}async function mt(){let e=await qe();We.set(e.data)}function gt(e,t,n){let r,o,s=!1;const c=We.subscribe(e=>{n(0,r=e),n(1,o=e.name)});async function i(e){if(e){let t=await async function(e,t){return Te.put(`https://fivexo.herokuapp.com/api/player/${e}/${t}`)}(r.id,e);We.set(t.data)}}S(c),setInterval(async()=>{let e=await Be(r.id);n(0,r=e.data)},1e3);return[r,o,s,i,c,()=>{n(2,s=!s)},function(){o=this.value,n(1,o)},()=>i(o)]}class vt extends Y{constructor(e){super(),z(this,e,gt,ht,s,{})}}function wt(t){let n;return{c(){n=p("main"),n.textContent="opponentbox"},m(e,t){l(e,n,t)},p:e,i:e,o:e,d(e){e&&f(n)}}}class yt extends Y{constructor(e){super(),z(this,e,null,wt,s,{})}}function bt(){xt.connected||(at=xt().open()),at.onmessage=function(e){try{console.log(JSON.parse(e.data)),Ve.set(JSON.parse(e.data)),console.log(i(Ve).id),function(){$t.connected||(ut=$t().open());ut.onmessage=function(e){try{if(JSON.parse(e.data).hasOwnProperty("character")&&JSON.parse(e.data).hasOwnProperty("turn"))Ke.set(JSON.parse(e.data));else{Xe.set(JSON.parse(e.data));const t=i(Ke);t.turn=i(Xe).turn,Ke.set(t)}console.log(JSON.parse(e.data))}catch(e){console.log(e)}},ut.onopen=function(e){Ge.set(ut),console.log("game socket opened")},ut.onclose=function(){console.log("gamesocket closed"),$t.connected=!1}}(),xt.connected=!1,xt.socket.close()}catch(t){console.log(e.data)}},at.onopen=function(){at.send("queue")},at.onclose=function(){console.log("websocket closed"),xt.connected=!1}}function xt(){return{connected:!1,open:()=>(xt.connected=!0,new WebSocket("wss://fivexo.herokuapp.com/queue/"+i(We).id))}}function $t(){return{connected:!1,open:()=>($t.connected=!0,new WebSocket(`wss://fivexo.herokuapp.com/play/${i(Ve).id}/${i(We).id}`))}}function kt(t){let n,r,o,s;return{c(){n=p("main"),r=p("button"),o=h("PLAY"),r.disabled=t[0]},m(e,c,i){l(e,n,c),u(n,r),u(r,o),i&&s(),s=v(r,"click",t[5])},p(e,[t]){1&t&&(r.disabled=e[0])},i:e,o:e,d(e){e&&f(n),s()}}}function Ct(e,t,n){let{init:r}=t,o=!1;const s=Ve.subscribe(e=>{n(0,o=void 0!==e&&e.gameFound)}),c=Xe.subscribe(e=>{void 0!==e&&n(0,o=!e.gameOver)});function i(){bt(),n(2,r=!r)}S(s);return e.$set=e=>{"init"in e&&n(2,r=e.init)},[o,i,r,s,c,()=>i()]}class St extends Y{constructor(e){super(),z(this,e,Ct,kt,s,{init:2})}}function Et(e){for(var t=e+"=",n=decodeURIComponent(document.cookie).split(";"),r=0;r<n.length;r++){for(var o=n[r];" "==o.charAt(0);)o=o.substring(1);if(0==o.indexOf(t))return o.substring(t.length,o.length)}return""}function Nt(e){let t;const n=new ft({});return{c(){I(n.$$.fragment)},m(e,r){M(n,e,r),t=!0},i(e){t||(D(n.$$.fragment,e),t=!0)},o(e){F(n.$$.fragment,e),t=!1},d(e){J(n,e)}}}function Ot(e){let t;const n=new ft({});return{c(){I(n.$$.fragment)},m(e,r){M(n,e,r),t=!0},i(e){t||(D(n.$$.fragment,e),t=!0)},o(e){F(n.$$.fragment,e),t=!1},d(e){J(n,e)}}}function Rt(e){let t,n,o,s,c,i,a,d,h,g,v,y,b;const x=new vt({}),$=[Ot,Nt],k=[];function C(e,t){return e[0]?0:1}function S(t){e[1].call(null,t)}i=C(e),a=k[i]=$[i](e);let E={};void 0!==e[0]&&(E.init=e[0]);const O=new St({props:E});N.push(()=>function(e,t,n){const r=e.$$.props[t];void 0!==r&&(e.$$.bound[r]=n,n(e.$$.ctx[r]))}(O,"init",S));const A=new He({}),T=new yt({});return{c(){t=p("main"),n=p("div"),o=p("div"),I(x.$$.fragment),s=m(),c=p("div"),a.c(),d=m(),I(O.$$.fragment),g=m(),I(A.$$.fragment),v=m(),y=p("div"),I(T.$$.fragment),w(o,"class","leftThing svelte-ui853w"),w(c,"class","content svelte-ui853w"),w(y,"class","rightThing svelte-ui853w"),w(n,"class","container svelte-ui853w"),w(t,"class","svelte-ui853w")},m(e,r){l(e,t,r),u(t,n),u(n,o),M(x,o,null),u(n,s),u(n,c),k[i].m(c,null),u(c,d),M(O,c,null),u(c,g),M(A,c,null),u(n,v),u(n,y),M(T,y,null),b=!0},p(e,[t]){let n=i;i=C(e),i!==n&&(_={r:0,c:[],p:_},F(k[n],1,1,()=>{k[n]=null}),_.r||r(_.c),_=_.p,a=k[i],a||(a=k[i]=$[i](e),a.c()),D(a,1),a.m(c,d));const o={};var s;!h&&1&t&&(h=!0,o.init=e[0],s=()=>h=!1,R.push(s)),O.$set(o)},i(e){b||(D(x.$$.fragment,e),D(a),D(O.$$.fragment,e),D(A.$$.fragment,e),D(T.$$.fragment,e),b=!0)},o(e){F(x.$$.fragment,e),F(a),F(O.$$.fragment,e),F(A.$$.fragment,e),F(T.$$.fragment,e),b=!1},d(e){e&&f(t),J(x),k[i].d(),J(O),J(A),J(T)}}}async function At(){if(Et("playerId")){const e=Et("playerId"),t=await Be(e);console.log(t.data);const n=t.data;We.set(n)}else{const e=(await qe()).data;!function(e,t,n){var r=new Date;r.setTime(r.getTime()+24*n*60*60*1e3);var o="expires="+r.toUTCString();document.cookie=e+"="+t+";"+o+";path=/"}("playerId",e.id,360),We.set(e)}}function Tt(e,t,n){let r=!0;var o;return console.log("Rendergame: "+r),o=async()=>{await At()},C().$$.on_mount.push(o),[r,function(e){r=e,n(0,r)}]}return new class extends Y{constructor(e){super(),z(this,e,Tt,Rt,s,{})}}({target:document.body})}();
//# sourceMappingURL=bundle.js.map
