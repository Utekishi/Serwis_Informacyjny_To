		
function klikniecie(){
			 console.log("klikniecie:"+this.firstChild.src+" c: "+this.firstChild.getAttribute("class"));
			   this.firstChild.style.border = this.firstChild.style.border.replace("dashed","dotted");
			 
			 
			 if(this.parentElement.getAttribute("class")=="thumb"){
				 this.parentElement.setAttribute("class","big");
			}else{
				 this.parentElement.setAttribute("class","thumb");
			}
			 loadBigImage(this.firstChild);
			
	
}
		