using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using UnityEngine.UI;

using UnityEngine.SceneManagement;

public class Timer : MonoBehaviour
{
    private float timer = 60f;

    public TextMesh text;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        timer -= Time.deltaTime;
        
        text.text = ((int)(timer % 60)).ToString();

        endGame();
    }

    void endGame(){
        if(timer <= 0){
            SceneManager.LoadScene("EndCard");
        }
    }
}
