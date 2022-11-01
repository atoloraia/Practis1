name: Trigger tests for run on BETA
description: Triggers automated tests for a test run
author: Anastasiya Tsitova
version: 1.0
includes: ^runs/view
excludes:

js:
$(document).ready(
	function() {

		/* Create the button */
		var button = $('<div class="toolbar content-header-toolbar"><a class="toolbar-button toolbar-button-last toolbar-button-first content-header-button button-start" href="javascript:void(0)">Run on BETA</a></div>');

		/* Add it to the toolbar */
		$("#content-header .content-header-inner").prepend(button);

		/* Bind the click event to trigger the automated tests */
		$("a", button).click(
			function() {
				runCI(uiscripts.context.run.id);
				console.log(caseIds);
				return false;
			}
		);

		/* Call CI */
		function runCI(testRunId) {
			var data = JSON.stringify({
			  "buildType": {
			    "id": "Practis_Dev_DevE2eTest"
			  },
			  "properties": {
			    "property": [
			      {
			        "name": "env.TEST_RAIL_TEST_RUN",
			        "value": testRunId
			      },
			      {
			        "name": "env.AUTOMATION_RUN_CI",
			        "value": "true"
			      },
			      {
			        "name": "env.WEB_USER_ID",
			        "value": "5209"
			      },
			      {
			        "name": "env.WEB_USER_LOGIN",
			        "value": "automationtests_practis@tula.co"
			      },
			      {
			        "name": "env.WEB_USER_PASSWORD",
			        "value": "Qwerty1234*"
			      },
			      {
			        "name": "env.WEB_APP_URL",
			        "value": "https://portal-beta.gopractis.com"
			      },
			      {
			        "name": "env.WEB_APP_ADMIN_URL",
			        "value": "https://admin-beta.gopractis.com"
			      },
			      {
			        "name": "env.WEB_REST_PRACTIS_URL",
			        "value": "https://api-beta.gopractis.com"
			      },
			      {
			        "name": "env.WEB_REST_PRACTIS_V2_URL",
			        "value": "https://api-beta-v2.gopractis.com"
			      }
			    ]
			  }
			});

			var xhr = new XMLHttpRequest();
			xhr.withCredentials = true;

			xhr.addEventListener("readystatechange", function () {
			  if (this.readyState === 4) {
			    console.log(this.responseText);
			  }
			});

			xhr.open("POST", "https://builder.tula.co/app/rest/latest/buildQueue");
			xhr.setRequestHeader("content-type", "application/json");
			xhr.setRequestHeader("authorization", "Bearer eyJ0eXAiOiAiVENWMiJ9.QlhzVjR3QWZnSU1Pd3JmTklVekFrYndOTUZB.ZGUwOWMzMjMtOGU2ZC00OWMyLWI5YTEtMjAwYmQ4NWZkMjZi");

			xhr.send(data);
		}
	}
);