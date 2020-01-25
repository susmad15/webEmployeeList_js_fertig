/* global fetch */

class selectCreator {

    constructor(selectObject, url, data) {
        this.selectObject = selectObject;
        this.url = url;
        this.data = data;
    }

    create = () => {
        this.fetchData(this.selectObject);
    }

    fetchData = () => {
        fetch(this.url,
                {
                    method: 'post',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: this.data
                }
        )
                .then(
                        response => {
                            response.json()
                                    .then(
                                            data => {
                                                this.setupSelect(data);
                                            }
                                    );
                        }
                );
    }

    setupSelect = (json) => {
        let selectString = "";

        for (let i = 0; i < json.length; i++)
        {
            let optionText = (json[i].name == null) ? "id=" +json[i].id : json[i].name;
            selectString += '<option value="' + json[i].id + '">' + optionText +'</option>';
        }

        document.getElementById(this.selectObject).innerHTML = selectString;
    }

}