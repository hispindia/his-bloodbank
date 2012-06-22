CKEDITOR.plugins.add( 'formtextbox', {
	init: function( editor ){
		editor.addCommand( 'insertformtextbox',{
			exec : function( editor ) {    						
				tb_show("testing", "selectObsPopup.form?type=textbox&modal=true&height=120&width=500");
			}
		});
		
		editor.ui.addButton( 'formtextbox', {
			label: 'Insert formtextbox',
			command: 'insertformtextbox',
			icon: this.path + 'images/formtextbox.png'
		});
	}
});