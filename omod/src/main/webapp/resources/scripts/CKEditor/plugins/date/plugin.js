CKEDITOR.plugins.add( 'date', {
	init: function( editor ){
		editor.addCommand( 'insertdatebox',{
			exec : function( editor ) {    						
				tb_show("testing", "selectObsPopup.form?type=date&modal=true&height=120&width=500");
			}
		});
		
		editor.ui.addButton( 'date', {
			label: 'Insert datebox',
			command: 'insertdatebox',
			icon: this.path + 'images/calendar.gif'
		});
	}
});