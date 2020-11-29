function testEmail() {
    var regExp = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/g;
    var email = document.getElementById("email").value;

    var rezultat = email.match(regExp);

    if (rezultat != null)
        return true;
      else {
        alert("Email nije u dobrom formatu!");
        document.getElementById("email").focus();
        document.getElementById("email").value="";
        return false;
    }
}

    function zabranaUnosaSpecKarIme()
    {
            var unos=/[^a-zA-Z]/;

        var user=document.getElementById("ime").value;

        if(user!=="")
        {
            if(unos.test(user))
            {
                alert("Zabranjen je unos specijalnih karaktera u imenu!");
                document.getElementById("ime").focus();
                return false;
            }
        }
    }
function zabranaUnosaSpecKarPrezime()
{
    var unos=/[^a-zA-Z0-9]/;

    var user=document.getElementById("prezime").value;

    if(user!=="")
    {
        if(unos.test(user))
        {
            alert("Zabranjen je unos specijalnih karaktera u prezimenu!");
            document.getElementById("prezime").focus();
            return false;
        }
    }
}

