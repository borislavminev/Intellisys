gsap.to(".logo", {duration: 2, x:50});
gsap.to(".transbox", {duration: 2, backgroundColor:"#EDEDED",borderRadius:"10%",ease:"slow"});
TweenLite.set(".presen-text", {visibility:"visible"})
TweenMax.staggerFrom(".presen-text", 7.5, {opacity:0}, 0.2); 

 