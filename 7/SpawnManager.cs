using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnManager : MonoBehaviour
{
    public GameObject AI;

    // Start is called before the first frame update
    void Start()
    {
        //AI.AddComponent<CharacterController>();

        Instantiate(AI, new Vector3(0f, 1f, 0f), Quaternion.identity);
        //Instantiate(Player, new Vector3(0f, 1f, 0f), Quaternion.identity);
    }
}
