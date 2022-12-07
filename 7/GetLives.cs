using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GetLives : MonoBehaviour
{
    private float lives = 0;

    public TextMesh text;
    
    //public TextMesh text;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        lives = FindObjectOfType<HealthManager>().getLives();
        
        text.text = (lives).ToString();
    }
}
