Installation instruction for BB module v0.0.1.2.

Prerequisites:
HtmlFormEntry module v1.6.8 successfully installed.

Install omod using admin interface.

After installation successfully complete, create 3 encounter types using admin panel - Manage encounter types:
Name			Description
Questionnaire	BB Questionnaire
BB Tests		BB Tests
Blood Sample	Blood Sample

Create patient identifier type named Donor Identifier
Create location via admin manage locations add location named Blood Bank

Start creating 3 html forms using admin - manage forms.
For this select Basic form (v.0.1.1) and press duplicate. A new screen opens, change the name to Questionnaire and 
select Questionnaire as encounter type from drop down. save the form. Do the same for other two modules with names 
BB Tests and BB Sample and select BB Tests and Blood Sample as encounter type respectively.

Now create 3 html forms using admin - manage html forms.

Click add and give name Questionnaire and select Questionnnaire v0.1 (1) as form and add this in html text area:

<htmlform>
	<macros>
		bgcolor=#e0e0e0
	</macros>

	This is a form

	<table>
		<tr>
			<td style="background-color: $bgcolor">
				This is the left column.
			</td>
			<td>
				<obsgroup groupingConceptId="3">
					<!-- CD4 -->
					<obs conceptId="1" labelNameTag="default" showDate="true" dateLabel="Date:"/>

					<!-- Weight -->
					<obs conceptId="2" labelNameTag="default"/>
				</obsgroup>
			</td>
		</tr>
	</table>
	<submit/>
	<p/> Encounter info <br/>
	Date: <encounterDate/> <br/>
	Location: <encounterLocation/> <br/>
	Provider: <encounterProvider/> <br/>
</htmlform>
 
save the form. Make sure concepts with id 1 and 2 are present in your system. 

Do the same for other two modules with name BB Tests and Blood Sample with the same html copied into html text area.

Now shift to admin global properties and make sure the following are set:
name 										value
bloodbank.donor.dilema						patient
bloodbank.donorIdTypeId 					exact id like 5 or 7 which is patient identification type id (Pointing mouse
											in Admin - Patient identifier type management on the name of identifier type
											will show the exact id at the status bar of browse)
bloodbank.expiry.period						number of days blood is valid, like 35
bloodbank.location.id						exact location id like 4, see bloodbank.patientIdTypeId on how to get this id
bloodbank.number.of.ids						number of preprinted ids, like 30
bloodbank.order.concepts					order concepts, concept ids assigned by billing service, comma separated
											if more than one
bloodbank.patientIdTypeId					exact id of patient identifier number, see bloodbank.patientIdTypeId on how
bloodbank.question.enctype.id				exact id of encounter type for questionnaire, see bloodbank.patientIdTypeId
bloodbank.question.formId					exact id of form for questionnaire, see bloodbank.patientIdTypeId
bloodbank.question.valid.count				number of observation to be filled to call result valid
bloodbank.result.enctype.id					exact id of encounter type for result, see bloodbank.patientIdTypeId
bloodbank.result.formId						exact id of form for result, see bloodbank.patientIdTypeId
bloodbank.result.valid.count				number of observation to be filled to call result valid	
bloodbank.test.enctype.id					exact id of encounter type for test, see bloodbank.patientIdTypeId
bloodbank.test.formId						exact id of form for test, see bloodbank.patientIdTypeId
bloodbank.test.valid.count					number of observation to be filled to call test valid	

go to Blood Bank menu and test the following:

1. creating new donor with default identifier
2. creating new donor with preprinted identifier
3. creating new donor with default identifier from existing patient
4. creating new donor with preprinted identifier from existing patient
5. adding questionnaire to donor record
6. adding test to donor record
7. adding result to donor record
8. see blood stock
9. see current orders for blood

Howto:

1. clikc Add Donor, type in the name, click save, DONE

2. click ADd Donor, type in the name, clcik preprinted id checkbox, type in the preprinted id, validate, save, DONE

3. Follow 1, click checkbox of matching patient in options column, confirm, DONE

4. Follow 2,  click checkbox of matching patient in options column, confirm, DONE

5. click Add Donor, type in the name, click on the name of found donor, click edit icon, fill in the form, save, DONE
6. follow 5, this time click edit on test field
7. follow 5, this time edit result
8. click view stock, you will be given all valid stock
9. clcik lab and cross match, you will see list of current blood orders.



