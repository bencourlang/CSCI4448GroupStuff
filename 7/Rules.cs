using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using UnityEngine.SceneManagement;

public class Rules : MonoBehaviour
{
    public void GoToRules(){
        SceneManager.LoadScene("HowToPlay");
    }
}
