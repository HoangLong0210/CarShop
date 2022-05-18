<script type="text/javascript">
         document.addEventListener("DOMContentLoaded", function(event) {
         
         const showNavbar = (toggleId, navId, bodyId, headerId) =>{
         const toggle = document.getElementById(toggleId),
         nav = document.getElementById(navId),
         bodypd = document.getElementById(bodyId),
         headerpd = document.getElementById(headerId)
         
         // Validate that all variables exist
         if(toggle && nav && bodypd && headerpd){
         toggle.addEventListener('click', ()=>{
         // show navbar
         nav.classList.toggle('show')
         // change icon
         toggle.classList.toggle('bx-x')
         // add padding to body
         bodypd.classList.toggle('body-pd')
         // add padding to header
         headerpd.classList.toggle('body-pd')
         })
         }
         };
         
         showNavbar('header-toggle','nav-bar','body-pd','header')
         
         /*===== LINK ACTIVE =====*/
         const linkColor = document.querySelectorAll('.nav_link')
         
         function colorLink(){
         if(linkColor){
         linkColor.forEach(l=> l.classList.remove('active'));
         this.classList.add('active');
         }
         };
         
         
         function openModalFuntion(){
         	$('#exampleModalCenter').modal('show')
         };
         linkColor.forEach(l=> l.addEventListener('click', colorLink))
         
         // Your code to run since DOM is loaded and ready
         });
      </script>
      <script type="text/javascript">
		    function EnableDisableTextBox() {
		        var rlink = document.getElementById("radiolink");
		        var rfile = document.getElementById("radiofile");
		        var inlink = document.getElementById("inputlink");
		        var infile = document.getElementById("inputfile");
		        inlink.readOnly = rlink.checked ? false : true;
		        infile.disabled = rfile.checked ? false : true;
		        if (inlink.readOnly) {
		        	if(inlink.value == '') inlink.value = 'Link image';
		        }
		        if (infile.disabled) {
		        	infile.value = '';
		        }
		    }
		</script>
		<script type="text/javascript">
		function submitForm() {
			   // Get the first form with the name
			   // Usually the form name is not repeated
			   // but duplicate names are possible in HTML
			   // Therefore to work around the issue, enforce the correct index
			   var frm = document.getElementsByName('carform')[0];
			   frm.submit(); // Submit the form
			   frm.reset();  // Reset all form data
			   return false; // Prevent page refresh
			}
		</script>
		
<script>
var editor = CKEDITOR.replace( 'messagebody' );
CKFinder.setupCKEditor(ckeditor, '${pageContext.request.contextPath}/resources/ckfinder/');
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>